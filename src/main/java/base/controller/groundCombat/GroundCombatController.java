package base.controller.groundCombat;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.controller.CommandResponse;
import base.controller.global.GlobalCommandController;
import base.controller.invasion.*;
import base.model.GameState;
import base.user.CommandRequestable;
import base.view.MessageString;
import board.Planet;
import board.Tile;
import board.TileObject;
import player.Player;
import player.units.Ships.Ship;
import player.units.Structures.PDS;
import player.units.Structures.Structure;
import player.units.Unit;

import java.util.ArrayList;

public class GroundCombatController extends AbstractController {
    private Player invader;
    private Player defender;

    private TileObject planet;
    private GameState gameState;

    private ArrayList<Unit> defenderUnits;
    private ArrayList<Unit> invaderUnits;

    private ArrayList<PDS> pdsForSpaceCannon;
    private ArrayList<Integer> diceValues;

    private Integer hitDefenderValue;
    private Integer hitInvaderValue;

    public GroundCombatController(CommandRequestable userInterface, GameState gameState,
                                  GlobalCommandController globalCommandController, TileObject planet, Player player) {
        super(userInterface, globalCommandController);
        //super.putCommand("space-cannon", new PlayerSpaceCannon(this));

        this.invader = player;
        this.defender = null;
        this.planet = planet;
        this.gameState = gameState;

        this.invaderUnits = new ArrayList<>();
        this.defenderUnits = new ArrayList<>();

        ArrayList<Unit> planetUnits = this.gameState.getTileArmyManager().getUnit(planet);
        for (Unit unit: planetUnits) {
            if (unit.getRace() == player.getRace() && !(unit instanceof Structure)) {
                this.invaderUnits.add(unit);
            }
            else {
                defender = Player.getRacePlayerManager().getPlayer(unit.getRace());
                if (!(unit instanceof Structure)) {
                    this.defenderUnits.add(unit);
                }
            }
        }

        pdsForSpaceCannon = new ArrayList<>();
        diceValues = new ArrayList<>();
        hitDefenderValue = 0;
        hitInvaderValue = 0;
    }

    public ArrayList<PDS> getPdsForSpaceCannon() {
        return pdsForSpaceCannon;
    }
    public ArrayList<Integer> getDiceValues() {
        return diceValues;
    }
    public Integer getHitValue(Player player) {
        return player == defender ? hitDefenderValue : hitInvaderValue;
    }
    public TileObject getPlanet() {
        return planet;
    }
    public ArrayList<Unit> getUnits(Player player) {
        return player == defender ? defenderUnits : invaderUnits;
    }

    private void assignHits(Player player) {
        userInterface.displayView(new MessageString(player.getName() + " choose " + getHitValue(player) + " units for hit"));

        CommandResponse response = CommandResponse.DECLINED;
        AbstractCommand command = new PlayerAssignHits(this, player);
        while (response == CommandResponse.DECLINED) {
            command.inputCommand(userInterface);
            response = command.execute(player);
        }
    }
    private void rollDices(Player player, Integer hitValue, int numDices) {
        AbstractCommand command = new PlayerRollDices(this);
        CommandResponse response = CommandResponse.DECLINED;

        while (diceValues.size() != numDices) {
            command.inputCommand(userInterface);
            response = command.execute(player);

            if (response == CommandResponse.DECLINED) {
                continue;
            }
            if (diceValues.size() != numDices) {
                userInterface.displayView(new MessageString("Please roll more dices. Now you roll: " + diceValues.toString()));
            }
        }

        hitValue = 0;
        for (int i = 0; i < diceValues.size(); ++i) {
            if (pdsForSpaceCannon.get(i).canHitFromSpaceCannon(diceValues.get(i))) {
                ++hitValue;
            }
        }

        userInterface.displayView(new MessageString(player.getName() + " damage " + hitValue + " units and roll: " + diceValues.toString()));
    }

    @Override
    public CommandResponse start() {
        AbstractCommand command = new PlayerSpaceCannon(this);
        CommandResponse response = CommandResponse.DECLINED;

        if (defender == null) {
            return CommandResponse.ACCEPTED;
        }

        while (response == CommandResponse.DECLINED) {
            command.inputCommand(userInterface);
            response = command.execute(defender);
            if (response == CommandResponse.DECLINED) {
                userInterface.displayView(new MessageString("Please input ONLY PDS, try again. Now added:"));
                for (PDS pds: pdsForSpaceCannon) {
                    userInterface.displayView(pds.getView(null));
                }
            }
        }
        userInterface.displayView(new MessageString("You successful add " + pdsForSpaceCannon.size() + " PDS"));

        rollDices(defender, hitInvaderValue, pdsForSpaceCannon.size());
        assignHits(invader);

        while (invaderUnits.size() != 0 && defenderUnits.size() != 0) {
            userInterface.displayView(new MessageString(invader.getName() + " have " + invaderUnits.size() + " combat units"));
            rollDices(invader, hitDefenderValue, invaderUnits.size());

            userInterface.displayView(new MessageString(defender.getName() + " have " + defenderUnits.size() + " combat units"));
            rollDices(defender, hitInvaderValue, defenderUnits.size());

            assignHits(defender);
            assignHits(invader);
        }

        if (invaderUnits.size() == 0) {
            userInterface.displayView(new MessageString(defender.getName() + " win!"));
        } else {
            userInterface.displayView(new MessageString(invader.getName() + " win!"));
        }

        return CommandResponse.ACCEPTED;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public AbstractCommand getExitCommand() {
        return null;
    }
}

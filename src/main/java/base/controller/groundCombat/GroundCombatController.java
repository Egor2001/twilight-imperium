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
import board.TileObject;
import player.Player;
import player.units.Structures.PDS;
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

    protected GroundCombatController(CommandRequestable userInterface, GameState gameState,
                                     GlobalCommandController globalCommandController, TileObject planet, Player player) {
        super(userInterface, globalCommandController);
        //super.putCommand("space-cannon", new PlayerSpaceCannon(this));

        this.invader = player;
        this.planet = planet;
        this.gameState = gameState;

        this.invaderUnits = new ArrayList<>();
        this.defenderUnits = new ArrayList<>();

        ArrayList<Unit> planetUnits = this.gameState.getTileArmyManager().getUnit(planet);
        for (Unit unit: planetUnits) {
            if (unit.getRace() == player.getRace()) {
                this.invaderUnits.add(unit);
            }
            else {
                defender = Player.getRacePlayerManager().getPlayer(unit.getRace());
                this.defenderUnits.add(unit);
            }
        }

        pdsForSpaceCannon = new ArrayList<>();
        diceValues = new ArrayList<>();
    }

    public ArrayList<PDS> getPdsForSpaceCannon() {
        return pdsForSpaceCannon;
    }

    public ArrayList<Integer> getDiceValues() {
        return diceValues;
    }

    @Override
    public CommandResponse start() {
        AbstractCommand command = new PlayerSpaceCannon(this);
        CommandResponse response = CommandResponse.DECLINED;

        if (defenderUnits.isEmpty()) {
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

        command = new PlayerRollDices(this);

        while (diceValues.size() != pdsForSpaceCannon.size()) {
            command.inputCommand(userInterface);
            response = command.execute(defender);

            if (response == CommandResponse.DECLINED) {
                continue;
            }
            if (diceValues.size() != pdsForSpaceCannon.size()) {
                userInterface.displayView(new MessageString("Please roll more dices. Now you roll: " + diceValues.toString()));
            }
        }



        return null;
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

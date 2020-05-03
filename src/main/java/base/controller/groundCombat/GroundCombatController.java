package base.controller.groundCombat;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.controller.CommandResponse;
import base.controller.global.GlobalCommandController;
import base.controller.invasion.*;
import base.model.GameState;
import base.user.CommandRequestable;
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
    }

    public ArrayList<PDS> getPdsForSpaceCannon() {
        return pdsForSpaceCannon;
    }

    @Override
    public CommandResponse start() {
        AbstractCommand command = null;
        CommandResponse response = null;

        for () {

        }

        return null;
    }

    @Override
    public GameState getGameState() {
        return null;
    }

    @Override
    public AbstractCommand getExitCommand() {
        return null;
    }
}

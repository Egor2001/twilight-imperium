package base.controller.invasion;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.controller.CommandResponse;

import base.controller.global.GlobalCommandController;
import base.model.GameState;
import base.user.CommandRequestable;
import board.Tile;
import board.TileObject;
import player.Player;

public class InvasionController extends AbstractController {
    private Player invader;
    private Tile tile;
    private GameState gameState;

    protected InvasionController(CommandRequestable userInterface, GameState gameState,
                                 GlobalCommandController globalCommandController, Tile tile, Player player) {
        super(userInterface, globalCommandController);
        super.putCommand("bombard", new PlayerInvasionBombard(this));
        super.putCommand("end-bombarding", new PlayerInvasionEndBombarding(this));
        super.putCommand("land-troops", new PlayerInvasionLandTroops(this));
        super.putCommand("stop-landing", new PlayerInvasionStopLanding(this));
        super.putCommand("defend", new PlayerInvasionDefend(this));
        super.putCommand("do-not-defend", new PlayerInvasionDoNotDefend(this));
        super.putCommand("assign-hits", new PlayerInvasionAssignHits(this));
        super.putCommand("make-rolls", new PlayerInvasionMakeRoll(this));

        this.invader = player;
        this.tile = tile;
        this.gameState = gameState;
    }



    @Override
    public CommandResponse start() {
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

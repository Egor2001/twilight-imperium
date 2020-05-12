package base.controller.tactic;

import base.controller.CommandResponse;
import base.model.GameState;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import base.view.MessageString;
import board.Tile;
import board.TileObject;
import player.Player;

public class PlayerTacticSetActiveTileCommand extends PlayerTacticCommand {
    private GameObjectTarget tileTarget;

    PlayerTacticSetActiveTileCommand(MoveController controller) {
        super(controller);
        tileTarget = null;
    }
    PlayerTacticSetActiveTileCommand(MoveController controller, MoveState moveState) {
        this(controller);
        tileTarget = null;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        tileTarget = userInterface.requestTarget("active tile");

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        GameState gameState = getController().getGameState();
        controller.getUserInterface().displayView(new MessageString("processing TACTIC command: SET_ACTIVE_TILE"));

        try {
            TileObject tileObject = ((Tile) gameState.getBoard().getObject(tileTarget)).GetSpace();
            ((MoveController) controller).getMoveState().setActiveTile(tileObject);
        }
        catch (Exception exception) {
            controller.getUserInterface().displayView(new MessageString(exception.getMessage() + ", try again"));
            return CommandResponse.DECLINED;
        }
        return CommandResponse.ACCEPTED;
    }
}

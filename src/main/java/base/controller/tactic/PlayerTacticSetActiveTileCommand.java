package base.controller.tactic;

import base.controller.CommandResponse;
import base.model.GameState;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import board.TileObject;
import player.Player;

public class PlayerTacticSetActiveTileCommand extends PlayerTacticCommand {
    private GameObjectTarget tileObjectTarget;

    PlayerTacticSetActiveTileCommand(MoveController controller) {
        super(controller);
        tileObjectTarget = null;
    }
    PlayerTacticSetActiveTileCommand(MoveController controller, MoveState moveState) {
        this(controller);
        tileObjectTarget = null;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        tileObjectTarget = userInterface.requestTarget("active tile object");

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        GameState gameState = ((MoveController) getController()).getGameState();
        System.out.println("processing TACTIC command: SET_ACTIVE_TILE");

        try {
            TileObject tileObject = (TileObject) gameState.getBoard().getObject(tileObjectTarget);
            ((MoveController) controller).getMoveState().setActiveTile(tileObject);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

package base.controller.tactic;

import base.controller.CommandResponse;
import base.model.GameState;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import board.TileObject;
import player.Player;

public class PlayerTacticSetActiveTileCommand extends PlayerTacticCommand {
    private GameObjectTarget tileObjectTarget;
    private MoveState moveState;

    PlayerTacticSetActiveTileCommand(MoveController controller) {
        super(controller);
        moveState = null;
        tileObjectTarget = null;
    }
    PlayerTacticSetActiveTileCommand(MoveController controller, MoveState moveState) {
        this(controller);
        this.moveState = moveState;
        tileObjectTarget = null;
    }

    @Override
    public void setMoveState(MoveState moveState) {
        this.moveState = moveState;
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
            moveState.setActiveTile(tileObject);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

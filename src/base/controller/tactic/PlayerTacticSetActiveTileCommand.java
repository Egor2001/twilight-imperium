package base.controller.tactic;

import base.controller.CommandResponse;
import base.controller.PlayerCommand;
import base.model.GameState;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import board.TileObject;
import player.Player;

public class PlayerTacticSetActiveTileCommand implements PlayerTacticCommand {
    private GameObjectTarget tileObjectTarget;
    private MoveState moveState;

    PlayerTacticSetActiveTileCommand() {
        moveState = null;
        tileObjectTarget = null;
    }
    PlayerTacticSetActiveTileCommand(MoveState moveState) {
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
    public CommandResponse execute(GameState gameState, Player player) {
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

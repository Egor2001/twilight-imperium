package base.controller.tactic;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import player.Player;

public class PlayerTacticClearCommand extends PlayerTacticCommand {
    MoveState moveState;

    public PlayerTacticClearCommand(MoveController controller) {
        super(controller);
        moveState = null;
    }

    @Override
    public void setMoveState(MoveState moveState) {
        this.moveState = moveState;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        System.out.println("processing TACTIC command: CLEAR");
        moveState.clear();

        return CommandResponse.ACCEPTED;
    }
}

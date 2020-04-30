package base.controller.tactic;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import player.Player;

public class PlayerTacticBreakCommand extends PlayerTacticCommand {
    public PlayerTacticBreakCommand(MoveController controller) {
        super(controller);
    }

    @Override
    public void setMoveState(MoveState moveState) {}

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        return CommandResponse.BREAK;
    }
}

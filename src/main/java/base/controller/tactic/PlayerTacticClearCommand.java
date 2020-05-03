package base.controller.tactic;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import base.view.MessageString;
import player.Player;

public class PlayerTacticClearCommand extends PlayerTacticCommand {
    public PlayerTacticClearCommand(MoveController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        controller.getUserInterface().displayView(new MessageString("processing TACTIC command: CLEAR"));
        ((MoveController) controller).getMoveState().clear();

        return CommandResponse.ACCEPTED;
    }
}

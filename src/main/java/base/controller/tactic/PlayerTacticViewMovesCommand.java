package base.controller.tactic;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import base.view.MessageString;
import player.Player;

import java.util.ArrayList;

public class PlayerTacticViewMovesCommand extends PlayerTacticCommand{
    PlayerTacticViewMovesCommand(MoveController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        controller.getUserInterface().displayView(new MessageString("processing TACTIC command: VIEW_ALL_UNIT"));
        controller.getUserInterface().displayView(new MessageString(((MoveController) controller).getMoveState().toString()));
        return CommandResponse.ACCEPTED;
    }
}

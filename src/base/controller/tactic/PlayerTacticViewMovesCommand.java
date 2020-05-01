package base.controller.tactic;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
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
        System.out.println("processing TACTIC command: VIEW_ALL_UNIT");
        System.out.println(((MoveController) controller).getMoveState().toString());
        return CommandResponse.ACCEPTED;
    }
}

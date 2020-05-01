package base.controller.tactic;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
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
        System.out.println("processing TACTIC command: CLEAR");
        ((MoveController) controller).getMoveState().clear();

        return CommandResponse.ACCEPTED;
    }
}

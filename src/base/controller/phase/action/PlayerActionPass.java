package base.controller.phase.action;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import player.Player;

public class PlayerActionPass extends PlayerActionCommand {
    public PlayerActionPass(ActionPhaseController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        System.out.println("processing ACTION command: END");
        return CommandResponse.END_EVENT;
    }
}

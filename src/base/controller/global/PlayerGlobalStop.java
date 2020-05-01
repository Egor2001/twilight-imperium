package base.controller.global;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.user.CommandRequestable;
import base.controller.CommandResponse;
import player.Player;

public class PlayerGlobalStop extends AbstractCommand {

    public PlayerGlobalStop(AbstractController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        return CommandResponse.END_EVENT;
    }
}

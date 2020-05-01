package base.controller.global;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.controller.CommandResponse;
import base.user.CommandRequestable;
import player.Player;

public class PlayerGlobalExit extends AbstractCommand {

    public PlayerGlobalExit(GlobalCommandController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        return CommandResponse.END_GAME;
    }
}

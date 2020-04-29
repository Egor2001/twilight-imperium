package base.controller.global;

import base.user.CommandRequestable;
import base.controller.CommandResponse;
import base.controller.PlayerCommand;
import base.model.GameState;
import player.Player;

public class PlayerGlobalStop implements PlayerCommand {
    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(GameState gameState, Player player) {
        return CommandResponse.END_EVENT;
    }
}

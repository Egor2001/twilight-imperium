package base.controller.global;

import base.controller.CommandController;
import base.user.CommandRequestable;
import base.controller.CommandResponse;
import base.controller.PlayerCommand;
import base.model.GameState;
import player.Player;

public class GlobalCommandController extends CommandController {

    private GameState gameState;
    private Player player;

    public GlobalCommandController(CommandRequestable userInterface, GameState gameState) {
        super(userInterface);
        this.gameState = gameState;
        this.player = null;

        super.putCommand("view", new PlayerGlobalView());
        super.putCommand("quit", new PlayerGlobalStop());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void start() {
        CommandResponse response = CommandResponse.DECLINED;
        PlayerCommand command = null;
        while (response != CommandResponse.END_EVENT) {
            response = CommandResponse.DECLINED;
            while (response == CommandResponse.DECLINED) {
                command = requestCommand(player, "global");
                response = command.execute(gameState, player);

                if (response == CommandResponse.DECLINED) {
                    userInterface.reportError("can't execute command");
                }
            }
        }
    }
}

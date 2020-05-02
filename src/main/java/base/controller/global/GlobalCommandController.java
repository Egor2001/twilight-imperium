package base.controller.global;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.user.CommandRequestable;
import base.controller.CommandResponse;
import base.model.GameState;
import player.Player;

public class GlobalCommandController extends AbstractController {

    private GameState gameState;
    private Player player;

    public GlobalCommandController(CommandRequestable userInterface, GameState gameState) {
        super(userInterface);
        this.gameState = gameState;
        this.player = null;

        super.putCommand("view", new PlayerGlobalView(this));
        super.putCommand("quit", new PlayerGlobalStop(this));
        super.putCommand("exit", new PlayerGlobalExit(this));
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public CommandResponse start() {
        CommandResponse response = CommandResponse.DECLINED;
        AbstractCommand command = null;
        while (response != CommandResponse.END_EVENT) {
            response = CommandResponse.DECLINED;
            while (response == CommandResponse.DECLINED) {
                command = requestCommand(player, "global");
                response = command.execute(player);

                if (response == CommandResponse.DECLINED) {
                    userInterface.reportError("can't execute command");
                }
                else if (response == CommandResponse.END_GAME) {
                    return response;
                }
            }
        }

        return CommandResponse.ACCEPTED;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public AbstractCommand getExitCommand() {
        return new PlayerGlobalExit(this);
    }
}

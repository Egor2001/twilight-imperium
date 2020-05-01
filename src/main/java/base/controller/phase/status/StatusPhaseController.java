package base.controller.phase.status;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.user.CommandRequestable;
import base.controller.CommandResponse;
import base.controller.global.GlobalCommandController;
import base.model.GameState;
import player.Player;

import java.util.ArrayList;

public class StatusPhaseController extends AbstractController {

    private GameState gameState;

    public StatusPhaseController(CommandRequestable userInterface, GameState gameState,
                                 GlobalCommandController globalCommandController) {
        super(userInterface, globalCommandController);
        this.gameState = gameState;

        super.putCommand("complete-mission", new PlayerStatusCompleteMission(this));
    }

    @Override
    public CommandResponse start() {
        ArrayList<Player> players = gameState.getPlayers();
        CommandResponse response = CommandResponse.DECLINED;

        PlayerStatusCommand playerStatus = null;
        for (int idx = 0; idx != players.size(); ++idx) {
            playerStatus = (PlayerStatusCommand) requestCommand(players.get(idx), "status");
            response = playerStatus.execute(players.get(idx));
            if (response == CommandResponse.END_GAME) {
                return response;
            }

            while (response == CommandResponse.DECLINED) {
                playerStatus = (PlayerStatusCommand) requestCommand(players.get(idx), "correct status");
                response = playerStatus.execute(players.get(idx));
                if (response == CommandResponse.END_GAME) {
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
        return new PlayerStatusCommand(this) {
            @Override
            public boolean inputCommand(CommandRequestable userInterface) {
                return false;
            }

            @Override
            public CommandResponse execute(Player player) {
                return CommandResponse.END_GAME;
            }
        };
    }
}

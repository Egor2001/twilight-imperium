package base.controller.phase.status;

import base.controller.CommandController;
import base.user.CommandRequestable;
import base.controller.CommandResponse;
import base.controller.global.GlobalCommandController;
import base.model.GameState;
import player.Player;

import java.util.ArrayList;

public class StatusPhaseController extends CommandController {

    private GameState gameState;

    public StatusPhaseController(CommandRequestable userInterface, GameState gameState,
                                 GlobalCommandController globalCommandController) {
        super(userInterface, globalCommandController);
        this.gameState = gameState;

        super.putCommand("complete-mission", new PlayerStatusCompleteMission());
    }

    @Override
    public void start() {
        ArrayList<Player> players = gameState.getPlayers();
        CommandResponse response = CommandResponse.DECLINED;

        PlayerStatusCommand playerStatus = null;
        for (int idx = 0; idx != players.size(); ++idx) {
            playerStatus = (PlayerStatusCommand) requestCommand(players.get(idx), "status");
            response = playerStatus.execute(gameState, players.get(idx));

            while (response == CommandResponse.DECLINED) {
                playerStatus = (PlayerStatusCommand) requestCommand(players.get(idx), "correct status");
                response = playerStatus.execute(gameState, players.get(idx));
            }
        }
    }
}

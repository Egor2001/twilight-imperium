package base.controller.phase.status;

import base.controller.CommandController;
import base.controller.CommandRequestable;
import base.controller.CommandResponse;
import base.model.GameState;
import base.model.Player;

import java.util.ArrayList;

public class StatusPhaseController extends CommandController {

    private GameState gameState;

    public StatusPhaseController(CommandRequestable userInterface, GameState gameState) {
        super(userInterface);
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

package base.controller.phase.strategy;

import base.controller.AbstractController;
import base.user.CommandRequestable;
import base.controller.CommandResponse;
import base.controller.global.GlobalCommandController;
import base.model.GameState;
import player.Player;

import java.util.ArrayList;

public class StrategyPhaseController extends AbstractController {

    private GameState gameState;

    public StrategyPhaseController(CommandRequestable userInterface, GameState gameState,
                                   GlobalCommandController globalCommandController) {
        super(userInterface, globalCommandController);
        this.gameState = gameState;

        super.putCommand("pick", new PlayerStrategyPick(this));
    }

    @Override
    public void start() {
        ArrayList<Player> players = gameState.getPlayers();
        CommandResponse response = CommandResponse.DECLINED;

        PlayerStrategyCommand playerStrategy = null;
        for (int idx = 0; idx != players.size(); ++idx) {
            playerStrategy = (PlayerStrategyCommand) requestCommand(players.get(idx), "strategy");
            response = playerStrategy.execute(players.get(idx));

            while (response == CommandResponse.DECLINED) {
                playerStrategy = (PlayerStrategyCommand) requestCommand(players.get(idx), "correct strategy");
                response = playerStrategy.execute(players.get(idx));
            }
        }
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }
}

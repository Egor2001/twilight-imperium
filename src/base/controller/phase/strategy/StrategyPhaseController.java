package base.controller.phase.strategy;

import base.controller.CommandController;
import base.controller.CommandRequestable;
import base.controller.CommandResponse;
import base.model.GameState;
import base.model.Player;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class StrategyPhaseController extends CommandController {

    private GameState gameState;

    public StrategyPhaseController(CommandRequestable userInterface, GameState gameState) {
        super(userInterface);
        this.gameState = gameState;

        super.putCommand("pick", new PlayerStrategyPick());
    }

    @Override
    public void start() {
        ArrayList<Player> players = gameState.getPlayers();
        CommandResponse response = CommandResponse.DECLINED;

        PlayerStrategyCommand playerStrategy = null;
        for (int idx = 0; idx != players.size(); ++idx) {
            playerStrategy = (PlayerStrategyCommand) requestCommand(players.get(idx), "strategy");
            response = playerStrategy.execute(gameState, players.get(idx));

            while (response == CommandResponse.DECLINED) {
                playerStrategy = (PlayerStrategyCommand) requestCommand(players.get(idx), "correct strategy");
                response = playerStrategy.execute(gameState, players.get(idx));
            }
        }
    }
}

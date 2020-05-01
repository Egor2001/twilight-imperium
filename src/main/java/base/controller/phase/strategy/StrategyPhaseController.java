package base.controller.phase.strategy;

import base.controller.AbstractCommand;
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
    public CommandResponse start() {
        ArrayList<Player> players = gameState.getPlayers();
        CommandResponse response = CommandResponse.DECLINED;

        PlayerStrategyCommand playerStrategy = null;
        for (int idx = 0; idx != players.size(); ++idx) {
            playerStrategy = (PlayerStrategyCommand) requestCommand(players.get(idx), "strategy");
            response = playerStrategy.execute(players.get(idx));
            if (response == CommandResponse.END_GAME) {
                return response;
            }

            while (response == CommandResponse.DECLINED) {
                playerStrategy = (PlayerStrategyCommand) requestCommand(players.get(idx), "correct strategy");
                response = playerStrategy.execute(players.get(idx));
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
        return new PlayerStrategyCommand(this) {
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

package base.controller.phase.strategy;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.controller.strategy.AbstractStrategy;
import base.controller.strategy.ConstructionStrategy;
import base.user.CommandRequestable;
import base.controller.CommandResponse;
import base.controller.global.GlobalCommandController;
import base.model.GameState;
import base.view.Viewable;
import player.Player;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StrategyPhaseController extends AbstractController {

    private GameState gameState;
    private ArrayList<String> strategyNameList;
    private ArrayList<Class<? extends AbstractStrategy>> strategyClassList;
    private ArrayList<Boolean> strategyUsedMask;

    public StrategyPhaseController(CommandRequestable userInterface, GameState gameState,
                                   GlobalCommandController globalCommandController) {
        super(userInterface, globalCommandController);
        this.gameState = gameState;
        this.strategyNameList = new ArrayList<>();
        this.strategyClassList = new ArrayList<>();
        this.strategyUsedMask = new ArrayList<>();

        for (int idx = 0; idx != this.gameState.getPlayers().size(); ++idx) {
            this.strategyNameList.add("construction");
            this.strategyClassList.add(ConstructionStrategy.class);
            this.strategyUsedMask.add(false);
        }

        super.putCommand("pick", new PlayerStrategyPick(this));
    }

    public Viewable getStrategiesView() {
        StringBuilder result = new StringBuilder();
        result.append("strategies: ");
        for (int idx = 0; idx != strategyNameList.size(); ++idx) {
            if (!strategyUsedMask.get(idx)) {
                result.append("[").append(idx).append("] = ").append(strategyNameList.get(idx)).append(" ");
            }
        }
        result.append("\n");

        return s -> s + result.toString();
    }

    public AbstractStrategy getStrategy(Player player, int idx) throws IllegalArgumentException {
        if (idx < 0 || idx >= strategyUsedMask.size() || strategyUsedMask.get(idx)) {
            throw new IllegalArgumentException("invalid strategy index");
        }

        AbstractStrategy strategy = null;

        try {
            Constructor<? extends AbstractStrategy> constructor =
                    strategyClassList.get(idx)
                            .getConstructor(StrategyPhaseController.class, Player.class, int.class);

            strategy = constructor.newInstance(this, player, idx);
        }
        catch (Exception exception) {
            throw new IllegalArgumentException("invalid construction");
        }
        strategyUsedMask.set(idx, true);

        return strategy;
    }

    @Override
    public CommandResponse start() {
        ArrayList<Player> players = gameState.getPlayers();
        CommandResponse response = CommandResponse.DECLINED;

        for (int idx = 0; idx != strategyUsedMask.size(); ++idx) {
            strategyUsedMask.set(idx, false);
        }

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

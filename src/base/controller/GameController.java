package base.controller;

import base.controller.commands.action.PlayerActionCommand;
import base.controller.commands.status.PlayerStatusCommand;
import base.controller.commands.strategy.PlayerStrategyCommand;
import base.model.Player;
import base.model.GameState;

public class GameController {

    private static GameController instance;

    private UserInterface userInterface;
    private GameState gameState;

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }

        return instance;
    }

    private GameController() {
        this.userInterface = new UserInterface();
        this.gameState = new GameState();
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public GameState getGameState() {
        return gameState;
    }

    public boolean gameInit() {
        Integer playersCnt = gameState.getPlayers().size();

        for (Integer playerIdx = 0; !playerIdx.equals(playersCnt); ++playerIdx) {
            Player newPlayer = userInterface.requestNewPlayer();

            if (newPlayer != null) {
                gameState.getPlayers().set(playerIdx, newPlayer);
            }
        }

        return true;
    }

    public boolean gameLoop() {
        for (Player player : gameState.getPlayers()) {
            strategyPhase(player);
        }

        for (Player player : gameState.getPlayers()) {
            actionPhase(player);
        }

        for (Player player : gameState.getPlayers()) {
            statusPhase(player);
        }

        return true;
    }

    protected boolean strategyPhase(Player player) {
        PlayerStrategyCommand command = userInterface.requestStrategy(player);
        if (command == null) {
            return false;
        }

        return gameState.handleCommand(player, command);
    }

    protected boolean actionPhase(Player player) {
        PlayerActionCommand command = userInterface.requestAction(player);
        if (command == null) {
            return false;
        }

        return gameState.handleCommand(player, command);
    }

    protected boolean statusPhase(Player player) {
        PlayerStatusCommand command = userInterface.requestStatus(player);
        if (command == null) {
            return false;
        }

        return gameState.handleCommand(player, command);
    }
}

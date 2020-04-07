package base.controller;

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

    public Boolean gameInit() {
        Integer playersCnt = gameState.getPlayers().size();

        for (Integer playerIdx = 0; !playerIdx.equals(playersCnt); ++playerIdx) {
            Player newPlayer = userInterface.requestNewPlayer();

            if (newPlayer != null) {
                gameState.getPlayers().set(playerIdx, newPlayer);
            }
        }

        return true;
    }

    public Boolean gameLoop() {
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

    protected Boolean strategyPhase(Player player) {
        PhaseController.PlayerStrategyCommand command =
                userInterface.requestStrategy(player);

        if (command == null) {
            return false;
        }

        gameState.handleStrategyCommand(player, command);

        return true;
    }

    protected Boolean actionPhase(Player player) {
        PhaseController.PlayerActionCommand command =
                userInterface.requestAction(player);

        if (command == null) {
            return false;
        }

        //command.procCommand();
        gameState.handleActionCommand(player, command);

        return true;
    }

    protected Boolean statusPhase(Player player) {
        PhaseController.PlayerStatusCommand command =
                userInterface.requestStatus(player);

        if (command == null) {
            return false;
        }

        gameState.handleStatusCommand(player, command);

        return true;
    }
}

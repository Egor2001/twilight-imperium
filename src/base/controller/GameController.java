package base.controller;

import base.controller.phase.PhaseController;
import base.model.Player;
import base.model.GameState;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Stack;

public class GameController {

    private static GameController instance;

    private CommandRequestable userInterface;
    private GameState gameState;

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController(System.in, System.out);
        }

        return instance;
    }

    private GameController(InputStream inputStream, PrintStream printStream) {
        this.userInterface = new UserInterface(inputStream, printStream);
        this.gameState = new GameState();
    }

    public CommandRequestable getUserInterface() {
        return userInterface;
    }

    public GameState getGameState() {
        return gameState;
    }

    public boolean start() {
        int playersCnt = gameState.getPlayers().size();

        for (int playerIdx = 0; playerIdx != playersCnt; ++playerIdx) {
            gameState.getPlayers().set(playerIdx, requestPlayer(playerIdx));
        }

        Stack<PhaseController> controllerStack = new Stack<>();
        controllerStack.push(new PhaseController(userInterface, gameState));
        controllerStack.pop().start();

        return true;
    }

    private Player requestPlayer(int idx) {
        Player player = null;

        String name = userInterface.requestName("player" + idx);
        String race = userInterface.requestName("race");
        while (player == null) {
            try {
                player = new Player(name, race);
            } catch (IllegalArgumentException exception) {
                race = userInterface.requestName("correct race");
                player = null;
            }
        }

        return player;
    }
}

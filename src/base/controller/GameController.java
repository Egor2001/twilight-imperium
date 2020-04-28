package base.controller;

import base.controller.global.GlobalCommandController;
import base.controller.phase.action.ActionPhaseController;
import base.controller.phase.status.StatusPhaseController;
import base.controller.phase.strategy.StrategyPhaseController;
import base.model.Player;
import base.model.GameState;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class GameController extends CommandController {

    private static GameController instance;
    private GameState gameState;

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController(System.in, System.out);
        }

        return instance;
    }

    private GameController(InputStream inputStream, PrintStream printStream) {
        super(new UserInterface(inputStream, printStream));
        this.gameState = new GameState();
        this.globalCommandController = new GlobalCommandController(this.userInterface, this.gameState);
    }

    public CommandRequestable getUserInterface() {
        return userInterface;
    }

    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void start() {
        int playersCnt = gameState.getPlayers().size();
        for (int playerIdx = 0; playerIdx != playersCnt; ++playerIdx) {
            gameState.getPlayers().set(playerIdx, requestPlayer(playerIdx));
        }

        ArrayList<CommandController> controllers = new ArrayList<>();
        controllers.add(new StrategyPhaseController(userInterface, gameState, globalCommandController));
        controllers.add(new ActionPhaseController(userInterface, gameState, globalCommandController));
        controllers.add(new StatusPhaseController(userInterface, gameState, globalCommandController));

        for (CommandController controller : controllers)
            controller.start();
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

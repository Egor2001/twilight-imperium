package base;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.controller.CommandResponse;
import base.controller.global.PlayerGlobalExit;
import base.user.CommandRequestable;
import base.controller.global.GlobalCommandController;
import base.controller.phase.action.ActionPhaseController;
import base.controller.phase.status.StatusPhaseController;
import base.controller.phase.strategy.StrategyPhaseController;
import base.user.UserInterface;
import player.Player;
import base.model.GameState;

import java.io.*;
import java.util.ArrayList;

/**
 * Root controller
 * processes main user actions
 * delegates processing other actions to other responsible controllers
 * implements singleton pattern
 * @see AbstractController
 * @author geome_try
 */
public class GameController extends AbstractController {

    private static GameController instance;
    //private static Object IOUtils;
    private GameState gameState;

    /**
     * Singleton getter and default constructor if no instance presents
     * @return unique class instance
     */
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController(System.in, System.out);
        }

        return instance;
    }

    /**
     * Singleton getter and constructor if no instance presents
     * @param inputStream input stream to read user commands from
     * @param printStream print stream to write game events' info to
     * @return unique class instance
     */
    public static GameController getInstance(InputStream inputStream, PrintStream printStream) {
        //BufferedReader reader = new BufferedReader(new FileReader(input_file))
        //new FileReader(input_file);

        if (instance == null) {
            instance = new GameController(inputStream, printStream);
        }

        return instance;
    }

    private GameController(InputStream inputStream, PrintStream printStream) {
        super(new UserInterface(inputStream, printStream));
        this.gameState = new GameState();
        this.globalCommandController = new GlobalCommandController(this.userInterface, this.gameState);
    }

    /**
     * UI object getter
     * @return user interface object
     */
    public CommandRequestable getUserInterface() {
        return userInterface;
    }

    /**
     * Game state object getter
     * @return game state
     */
    @Override
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Exit command builder to satisfy AbstractController interface
     * @see AbstractCommand
     * @return exit command for this controller
     */
    @Override
    public AbstractCommand getExitCommand() {
        return new PlayerGlobalExit(null);
    }

    /**
     * Controller's entry point function
     * @see CommandResponse
     * @return response of exit result
     */
    @Override
    public CommandResponse start() {
        int playersCnt = gameState.getPlayers().size();
        for (int playerIdx = 0; playerIdx != playersCnt; ++playerIdx) {
            gameState.getPlayers().set(playerIdx, requestPlayer(playerIdx));
        }

        CommandResponse response = CommandResponse.ACCEPTED;
        ArrayList<AbstractController> controllers = new ArrayList<>();
        controllers.add(new StrategyPhaseController(userInterface, gameState, globalCommandController));
        controllers.add(new ActionPhaseController(userInterface, gameState, globalCommandController));
        controllers.add(new StatusPhaseController(userInterface, gameState, globalCommandController));

        for (AbstractController controller : controllers) {
            response = controller.start();
            if (response == CommandResponse.END_GAME) {
                break;
            }
        }

        return CommandResponse.ACCEPTED;
    }

    /**
     * Requests user to customize its player
     * @see Player
     * @param idx user's game order number
     * @return player class object
     */
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

    /**
     * Deletes current instance
     * Warning! unsafe due to singleton pattern requirements violation
     * Should be removed in future versions
     */
    public static void Delete () {
        instance = null;
    }
}

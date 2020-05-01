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

public class GameController extends AbstractController {

    private static GameController instance;
    //private static Object IOUtils;
    private GameState gameState;

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController(System.in, System.out);
        }

        return instance;
    }

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

    public CommandRequestable getUserInterface() {
        return userInterface;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public AbstractCommand getExitCommand() {
        return new PlayerGlobalExit(null);
    }

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

    public static void Delete ()
    {
        instance = null;
    }
}

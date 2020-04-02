package base;

public class CGameController {

    private static CGameController instance;

    public static CGameController getInstance() {
        if (instance == null) {
            instance = new CGameController();
        }

        return instance;
    }

    private CGameController() {
        this.userInterface = new CUserInterface();
        this.gameState = new CGameState();
    }

    public CUserInterface getUserInterface() {
        return userInterface;
    }

    public CGameState getGameState() {
        return gameState;
    }

    public Boolean gameInit() {
        Integer playersCnt = gameState.getPlayers().size();

        for (Integer playerIdx = 0; !playerIdx.equals(playersCnt); ++playerIdx) {
            gameState.getPlayers().set(playerIdx, userInterface.requestNewPlayer());
        }

        return true;
    }

    public Boolean gameLoop() {
        for (CPlayer player : gameState.getPlayers()) {
            processActionCommand(player);
        }

        return true;
    }

    protected Boolean processActionCommand(CPlayer player) {
        CUserInterface.IPlayerCommand command =
                userInterface.requestAction(player);

        if (command == null) {
            return false;
        }

        command.procCommand();

        return true;
    }

    private CUserInterface userInterface;
    private CGameState gameState;
}

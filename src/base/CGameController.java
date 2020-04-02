package base;

public class CGameController {

    private static CGameController instance;

    private CUserInterface userInterface;
    private CGameState gameState;

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
            CPlayer newPlayer = userInterface.requestNewPlayer();

            if (newPlayer != null) {
                gameState.getPlayers().set(playerIdx, newPlayer);
            }
        }

        return true;
    }

    public Boolean gameLoop() {
        for (CPlayer player : gameState.getPlayers()) {
            strategyPhase(player);
        }

        for (CPlayer player : gameState.getPlayers()) {
            actionPhase(player);
        }

        for (CPlayer player : gameState.getPlayers()) {
            statusPhase(player);
        }

        return true;
    }

    protected Boolean strategyPhase(CPlayer player) {
        CUserInterface.IPlayerStrategyCommand command =
                userInterface.requestStrategy(player);

        if (command == null) {
            return false;
        }

        gameState.handleStrategyCommand(player, command);

        return true;
    }

    protected Boolean actionPhase(CPlayer player) {
        CUserInterface.IPlayerActionCommand command =
                userInterface.requestAction(player);

        if (command == null) {
            return false;
        }

        //command.procCommand();
        gameState.handleActionCommand(player, command);

        return true;
    }

    protected Boolean statusPhase(CPlayer player) {
        CUserInterface.IPlayerStatusCommand command =
                userInterface.requestStatus(player);

        if (command == null) {
            return false;
        }

        gameState.handleStatusCommand(player, command);

        return true;
    }
}

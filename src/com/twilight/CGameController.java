package com.twilight;

public class CGameController {

    public static final Integer PLAYER_CNT = 6;
    private static CGameController instance;

    public static CGameController getInstance() {
        if (instance == null) {
            instance = new CGameController();
        }

        return instance;
    }

    private CGameController() {
        this.currentPlayerIdx = 0;
        this.userInterface = new CUserInterface();
        this.gameState = new CGameState();
    }

    public CUserInterface getUserInterface() {
        return userInterface;
    }

    public CGameState getGameState() {
        return gameState;
    }

    public Boolean gameLoop() {
        if (processActionCommand(currentPlayerIdx)) {
            currentPlayerIdx = (currentPlayerIdx + 1)%6;
        }

        return false;
    }

    protected Boolean processActionCommand(Integer playerIdx) {
        CPlayer player = gameState.getPlayers().get(0);
        CUserInterface.IPlayerActionCommand actionCommand =
                userInterface.requestAction(player);

        return (actionCommand != null);
    }

    private Integer currentPlayerIdx;
    private CUserInterface userInterface;
    private CGameState gameState;
}

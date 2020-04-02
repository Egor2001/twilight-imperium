package base;

import java.util.ArrayList;

public class CGameState implements IUpdatable {

    public final Integer PLAYERS_CNT = 2;

    public CGameState() {
        this.board = new CBoard();
        this.players = new ArrayList<CPlayer>(6);

        for (Integer i = 0; i.compareTo(PLAYERS_CNT) != 0; ++i) {
            players.add(new CPlayer("", new CArmy()));
        }
    }

    public CBoard getBoard() {
        return board;
    }

    public ArrayList<CPlayer> getPlayers() {
        return players;
    }

    @Override
    public void update() {
        board.update();
        for (CPlayer player : players) {
            player.update();
        }
    }

    private CBoard board;
    private ArrayList<CPlayer> players;
}

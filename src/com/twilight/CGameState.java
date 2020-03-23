package com.twilight;

import java.util.ArrayList;

public class CGameState implements IUpdatable {

    public CGameState() {
        this.board = new CBoard();
        this.players = new ArrayList<CPlayer>(6);
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

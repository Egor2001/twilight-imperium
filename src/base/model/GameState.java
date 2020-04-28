package base.model;

import base.*;
import base.controller.PlayerCommand;
import tile.Board;
import tile.TileArmyController;

import java.util.ArrayList;

public class GameState implements Updatable {

    public final Integer PLAYERS_CNT = 2;

    private Board board;
    private ArrayList<Player> players;
    private TileArmyController tileArmyController;

    public GameState() {
        this.players = new ArrayList<Player>(6);
        this.tileArmyController = new TileArmyController();
        this.board = new Board(this.tileArmyController);
        for (Integer i = 0; i.compareTo(PLAYERS_CNT) != 0; ++i) {
            players.add(null);
        }
    }

    public Board getBoard() {
        return board;
    }
    public TileArmyController getController() { return tileArmyController; }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public TileArmyController getTileArmyController() {
        return tileArmyController;
    }

    @Override
    public void update() {
        //board.update();
        for (Player player : players) {
            player.update();
        }
    }
}

package base.model;

import base.user.GameObjectTarget;
import base.user.UserAcceptable;
import base.view.Viewable;
import player.Player;
import board.Board;
import board.TileArmyManager;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class GameState implements UserAcceptable, Updatable {

    public final Integer PLAYERS_CNT = 2;

    private Board board;
    private ArrayList<Player> players;
    private TileArmyManager tileArmyManager;

    public GameState() {
        this.players = new ArrayList<Player>(6);
        this.tileArmyManager = new TileArmyManager();
        this.board = new Board(this.tileArmyManager);
        for (Integer i = 0; i.compareTo(PLAYERS_CNT) != 0; ++i) {
            players.add(null);
        }
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public TileArmyManager getTileArmyManager() {
        return tileArmyManager;
    }

    @Override
    public void update() {
        //board.update();
        for (Player player : players) {
            player.update();
        }
    }

    public class View implements Viewable {

        public View() {}

        @Override
        public void display(Writer writer) throws IOException {
            writer.write(toString(""));
        }

        @Override
        public String toString(String s) {
            StringBuilder resultBuilder = new StringBuilder();
            for (Player player : getPlayers()) {
                resultBuilder.append(player.getView(null).toString("\t"));
                resultBuilder.append("\n");
            }

            resultBuilder.append(getBoard().getView(null).toString("\t"));

            return resultBuilder.toString();
        }

        @Override
        public String toString() {
            return toString("");
        }
    }

    @Override
    public Viewable getView(UserAcceptable parent) {
        return this.new View();
    }

    @Override
    public Viewable getView(UserAcceptable parent,
                            GameObjectTarget target) {
        return getView(parent);
    }

    @Override
    public Object getObject(GameObjectTarget target) throws Exception {
        if (target != null) {
            if (target instanceof Player.Target) {
                return players.get(target.getIndex()).getObject(target.getNext());
            }
            else if (target instanceof Board.Target) {
                return board.getObject(target.getNext());
            }
            else {
                throw new IllegalArgumentException("target has invalid type");
            }
        }

        return this;
    }
}

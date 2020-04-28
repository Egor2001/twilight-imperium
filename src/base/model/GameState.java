package base.model;

import base.*;
import base.controller.HierarchyController;
import base.controller.PlayerCommand;
import tile.Board;
import tile.TileArmyController;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class GameState implements HierarchyController.UserAcceptable, Updatable {

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

    public class View implements HierarchyController.Viewable {

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
    public HierarchyController.Viewable getView(HierarchyController.UserAcceptable parent) {
        return this.new View();
    }

    @Override
    public HierarchyController.Viewable getView(HierarchyController.UserAcceptable parent,
                                                HierarchyController.GameObjectTarget target) {
        return getView(parent);
    }

    @Override
    public Object getObject(HierarchyController.GameObjectTarget target) throws Exception {
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

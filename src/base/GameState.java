package base;

import ArmyUnits.Army;

import java.util.ArrayList;

public class GameState implements Updatable {

    public final Integer PLAYERS_CNT = 2;

    private Board board;
    private ArrayList<Player> players;

    public GameState() {
        this.board = new Board();
        this.players = new ArrayList<Player>(6);

        for (Integer i = 0; i.compareTo(PLAYERS_CNT) != 0; ++i) {
            players.add(new Player("", new Army()));
        }
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public void update() {
        board.update();
        for (Player player : players) {
            player.update();
        }
    }

    public Boolean handleStrategyCommand(Player player, UserInterface.IPlayerStrategyCommand command) {
        return handleCommand(player, command);
    }

    public Boolean handleActionCommand(Player player, UserInterface.IPlayerActionCommand command) {
        return handleCommand(player, command);
    }

    public Boolean handleStatusCommand(Player player, UserInterface.IPlayerStatusCommand command) {
        return handleCommand(player, command);
    }

    protected Boolean handleCommand(Player player, UserInterface.IPlayerCommand command) {
        assert (command != null) : "command is null";

        command.procCommand();

        return true;
    }
}

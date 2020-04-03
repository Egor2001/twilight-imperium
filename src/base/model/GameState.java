package base.model;

import base.*;
import base.controller.PhaseController;

import ArmyUnits.ArmyPlanet;

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

    public Boolean handleStrategyCommand(Player player, PhaseController.PlayerStrategyCommand command) {
        return handleCommand(player, command);
    }

    public Boolean handleActionCommand(Player player, PhaseController.PlayerActionCommand command) {
        return handleCommand(player, command);
    }

    public Boolean handleStatusCommand(Player player, PhaseController.PlayerStatusCommand command) {
        return handleCommand(player, command);
    }

    protected Boolean handleCommand(Player player, PhaseController.PlayerCommand command) {
        assert (command != null) : "command is null";

        command.procCommand();

        return true;
    }
}

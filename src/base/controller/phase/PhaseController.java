package base.controller.phase;

import base.controller.CommandRequestable;
import base.model.GameState;
import base.model.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PhaseController {

    private static HashMap<String, PlayerStrategyCommand> strategyCommandHashMap;
    private static HashMap<String, PlayerActionCommand> actionCommandHashMap;
    private static HashMap<String, PlayerStatusCommand> statusCommandHashMap;

    static {
        strategyCommandHashMap = new HashMap<>();
        actionCommandHashMap = new HashMap<>();
        statusCommandHashMap = new HashMap<>();

        strategyCommandHashMap.put("pick", new PlayerStrategyPick());

        actionCommandHashMap.put("move", new PlayerActionMove());
        actionCommandHashMap.put("add-unit", new PlayerActionAddUnit());

        statusCommandHashMap.put("complete-mission", new PlayerStatusCompleteMission());
    }

    private CommandRequestable userInterface;
    private GameState gameState;

    public PhaseController(CommandRequestable userInterface, GameState gameState) {
        this.userInterface = userInterface;
        this.gameState = gameState;
    }

    public void start() {
        ArrayList<Player> players = gameState.getPlayers();
        boolean response = false;

        PlayerStrategyCommand playerStrategy = null;
        for (int idx = 0; idx != players.size(); ++idx) {
            playerStrategy = requestStrategy(players.get(idx));
            response = playerStrategy.execute(gameState, players.get(idx));

            if (!response) {

            }
        }

        PlayerActionCommand playerActionFirst = null;
        boolean[] isPassed = new boolean[players.size()];
        for (int idx = 0; idx != players.size(); ++idx) {
            if (!isPassed[idx]) {
                playerActionFirst = requestAction(players.get(idx));
                response = playerActionFirst.execute(gameState, players.get(idx));

                if (!response) {
                    isPassed[idx] = true;
                }
            }
        }

        PlayerStatusCommand playerStatus = null;
        for (int idx = 0; idx != players.size(); ++idx) {
            playerStatus = requestStatus(players.get(idx));
            response = playerStatus.execute(gameState, players.get(idx));

            if (!response) {

            }
        }
    }

    public PlayerStrategyCommand requestStrategy(Player player) {
        PlayerStrategyCommand command = null;

        String name = userInterface.requestName(player.getName() + "'s strategy command");
        command = strategyCommandHashMap.get(name);
        while (command == null) {
            name = userInterface.requestName(player.getName() + "'s proper strategy command");
            command = strategyCommandHashMap.get(name);
        }
        command.inputCommand(userInterface);

        return command;
    }

    public PlayerActionCommand requestAction(Player player) {
        PlayerActionCommand command = null;

        String name = userInterface.requestName(player.getName() + "'s action command");
        command = actionCommandHashMap.get(name);
        while (command == null) {
            name = userInterface.requestName(player.getName() + "'s proper action command");
            command = actionCommandHashMap.get(name);
        }
        command.inputCommand(userInterface);

        return command;
    }

    public PlayerStatusCommand requestStatus(Player player) {
        PlayerStatusCommand command = null;

        String name = userInterface.requestName(player.getName() + "'s status command");
        command = statusCommandHashMap.get(name);
        while (command == null) {
            name = userInterface.requestName(player.getName() + "'s proper status command");
            command = statusCommandHashMap.get(name);
        }
        command.inputCommand(userInterface);

        return command;
    }
}

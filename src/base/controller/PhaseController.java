package base.controller;

import base.controller.commands.action.PlayerActionAddUnit;
import base.controller.commands.action.PlayerActionCommand;
import base.controller.commands.action.PlayerActionMove;
import base.controller.commands.status.PlayerStatusCommand;
import base.controller.commands.status.PlayerStatusCompleteMission;
import base.controller.commands.strategy.PlayerStrategyCommand;
import base.controller.commands.strategy.PlayerStrategyPick;

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

    public PhaseController() {}

    public PlayerStrategyCommand getStrategyCommand(String commandStr) {
        return strategyCommandHashMap.get(commandStr);
    }

    public PlayerActionCommand getActionCommand(String commandStr) {
        return actionCommandHashMap.get(commandStr);
    }

    public PlayerStatusCommand getStatusCommand(String commandStr) {
        return statusCommandHashMap.get(commandStr);
    }
}

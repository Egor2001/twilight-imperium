package base.controller;

import base.model.Player;

import java.util.HashMap;

public abstract class CommandController {

    protected HashMap<String, PlayerCommand> commandHashMap;
    protected CommandRequestable userInterface;

    public CommandController(CommandRequestable userInterface) {
        this.commandHashMap = new HashMap<>();
        this.userInterface = userInterface;
    }

    public abstract void start();

    protected void putCommand(String name, PlayerCommand command) {
        commandHashMap.put(name, command);
    }

    protected PlayerCommand requestCommand(Player player, String context) {
        PlayerCommand command = null;

        String name = userInterface.requestName(player.getName() + "'s " + context + " command");
        command = commandHashMap.get(name);
        while (command == null) {
            name = userInterface.requestName(player.getName() + "'s proper " + context + " command");
            command = commandHashMap.get(name);
        }
        command.inputCommand(userInterface);

        return command;
    }
}

package base.controller;

import base.controller.global.GlobalCommandController;
import base.model.Player;

import java.util.HashMap;

public abstract class CommandController {

    private static final String GLOBAL_COMMAND_NAME = "game";

    protected HashMap<String, PlayerCommand> commandHashMap;
    protected CommandRequestable userInterface;
    protected GlobalCommandController globalCommandController;

    protected CommandController(CommandRequestable userInterface) {
        this.commandHashMap = new HashMap<>();
        this.userInterface = userInterface;
        this.globalCommandController = null;
    }

    protected CommandController(CommandRequestable userInterface, GlobalCommandController globalCommandController) {
        this(userInterface);
        this.globalCommandController = globalCommandController;
    }

    public abstract void start();

    protected void putCommand(String name, PlayerCommand command) {
        commandHashMap.put(name, command);
    }

    protected PlayerCommand requestCommand(Player player, String context) {
        PlayerCommand command = null;
        String name = null;
        while (command == null) {
            name = userInterface.requestName(player.getName() + "'s " + context + " command");
            if (globalCommandController != null && name.equals(GLOBAL_COMMAND_NAME)) {
                globalCommandController.setPlayer(player);
                globalCommandController.start();
                continue;
            }

            command = commandHashMap.get(name);
            if (command == null) {
                userInterface.reportError("unknown command: " + name);
            }
        }
        command.inputCommand(userInterface);

        return command;
    }
}

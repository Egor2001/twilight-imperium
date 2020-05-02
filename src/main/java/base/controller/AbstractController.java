package base.controller;

import base.controller.global.GlobalCommandController;
import base.model.GameState;
import base.user.CommandRequestable;
import player.Player;

import java.util.HashMap;

public abstract class AbstractController {

    private static final String GLOBAL_COMMAND_NAME = "game";

    protected HashMap<String, AbstractCommand> commandHashMap;
    protected CommandRequestable userInterface;
    protected GlobalCommandController globalCommandController;

    protected AbstractController(CommandRequestable userInterface) {
        this.commandHashMap = new HashMap<>();
        this.userInterface = userInterface;
        this.globalCommandController = null;
    }

    protected AbstractController(CommandRequestable userInterface, GlobalCommandController globalCommandController) {
        this(userInterface);
        this.globalCommandController = globalCommandController;
    }

    public abstract CommandResponse start();

    protected void putCommand(String name, AbstractCommand command) {
        commandHashMap.put(name, command);
    }

    public abstract GameState getGameState();
    public abstract AbstractCommand getExitCommand();

    protected AbstractCommand requestCommand(Player player, String context) {
        AbstractCommand command = null;
        String name = null;
        while (command == null) {
            name = userInterface.requestName(player.getName() + "'s " + context + " command");
            if (globalCommandController != null && name.equals(GLOBAL_COMMAND_NAME)) {
                globalCommandController.setPlayer(player);
                CommandResponse response = globalCommandController.start();
                if (response == CommandResponse.END_GAME) {
                    return getExitCommand();
                }
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

    public CommandRequestable getUserInterface() {
        return userInterface;
    }

    public GlobalCommandController getGlobalCommandController() {
        return globalCommandController;
    }
}

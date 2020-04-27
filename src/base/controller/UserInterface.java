package base.controller;

import base.controller.commands.action.PlayerActionCommand;
import base.controller.commands.status.PlayerStatusCommand;
import base.controller.commands.strategy.PlayerStrategyCommand;
import base.model.Player;

import java.io.*;
import java.util.Scanner;

public class UserInterface {

    private final InputStream inputStream = System.in;
    private final PrintStream printStream = System.out;
    private final Scanner inputScanner = new Scanner(inputStream);

    private final PhaseController phaseController = new PhaseController();
    private final HierarchyController hierarchyController = new HierarchyController();

    public UserInterface() {
    }

    public Player requestNewPlayer() {
        printStream.println("Welcome! insert your name and race:");
        String name = inputScanner.nextLine();
        printStream.println("insert your race:");
        String race = inputScanner.nextLine();

        return new Player(name, race);
    }

    public HierarchyController.GameObjectTarget requestCommand() {
        return null;
    }

    public PlayerStrategyCommand requestStrategy(final Player player) {
        assert (player != null) : "player is null";

        printStream.print("STRATEGY phase, player: ");
        printStream.println(player.getName());

        String cmdName = inputScanner.next();
        PlayerStrategyCommand command = phaseController.getStrategyCommand(cmdName);
        while (command == null) {
            printStream.println("Invalid strategy type. Try again.");
            cmdName = inputScanner.next();
            command = phaseController.getStrategyCommand(cmdName);
        }

        while (!command.inputCommand(printStream, inputScanner)) {
            printStream.println("Invalid input. Try again.");
            printStream.flush();
        }

        return command;
    }

    public PlayerActionCommand requestAction(final Player player) {
        assert (player != null) : "player is null";

        printStream.print("ACTION phase, player: ");
        printStream.println(player.getName());

        String cmdName = inputScanner.next();
        PlayerActionCommand command = phaseController.getActionCommand(cmdName);
        while (command == null) {
            printStream.println("Invalid action type. Try again.");
            cmdName = inputScanner.next();
            command = phaseController.getActionCommand(cmdName);
        }

        while (!command.inputCommand(printStream, inputScanner)) {
            printStream.println("Invalid input. Try again.");
            printStream.flush();
        }

        return command;
    }

    public PlayerStatusCommand requestStatus(final Player player) {
        assert (player != null) : "player is null";

        printStream.print("STATUS phase, player: ");
        printStream.println(player.getName());

        String cmdName = inputScanner.next();
        PlayerStatusCommand command = phaseController.getStatusCommand(cmdName);
        while (command == null) {
            printStream.println("Invalid status type. Try again.");
            cmdName = inputScanner.next();
            command = phaseController.getStatusCommand(cmdName);
        }

        while (!command.inputCommand(printStream, inputScanner)) {
            printStream.println("Invalid input. Try again.");
            printStream.flush();
        }

        return command;
    }
}

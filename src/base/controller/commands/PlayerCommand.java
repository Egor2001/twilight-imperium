package base.controller.commands;

import base.model.GameState;
import base.model.Player;

import java.io.PrintStream;
import java.util.Scanner;

public interface PlayerCommand {
    Boolean inputCommand(PrintStream printStream, Scanner inputScanner);
    boolean procCommand(GameState gameState, Player player);
}

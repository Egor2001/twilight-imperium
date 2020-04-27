package base.controller.commands;

import base.model.GameState;
import base.model.Player;

import java.io.PrintStream;
import java.util.Scanner;

public interface PlayerCommand {
    boolean inputCommand(PrintStream printStream, Scanner inputScanner);
    boolean procCommand(GameState gameState, Player player);
}

package base.controller.commands.strategy;

import base.model.GameState;
import base.model.Player;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerStrategyPick implements PlayerStrategyCommand {

    public Integer strategyIdx = 0;

    public PlayerStrategyPick() {
        this.strategyIdx = 0;
    }

    public PlayerStrategyPick(Integer strategyIdx) {
        this.strategyIdx = strategyIdx;
    }

    @Override
    public boolean inputCommand(PrintStream printStream, Scanner inputScanner) {
        try {
            printStream.println("enter strategy number:");
            strategyIdx = inputScanner.nextInt();
        }
        catch (InputMismatchException exception) {
            printStream.flush();
            return false;
        }

        return true;
    }

    @Override
    public boolean procCommand(GameState gameState, Player player) {
        System.out.println("processing STRATEGY command: PICK");
        return true;
    }
}

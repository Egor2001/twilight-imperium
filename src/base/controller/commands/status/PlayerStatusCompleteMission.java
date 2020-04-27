package base.controller.commands.status;

import base.model.GameState;
import base.model.Player;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerStatusCompleteMission implements PlayerStatusCommand {

    public Integer missionIdx = 0;

    public PlayerStatusCompleteMission() {
        this.missionIdx = 0;
    }

    public PlayerStatusCompleteMission(Integer missionIdx) {
        this.missionIdx = missionIdx;
    }

    @Override
    public boolean inputCommand(PrintStream printStream, Scanner inputScanner) {
        try {
            printStream.println("enter mission number:");
            missionIdx = inputScanner.nextInt();
        }
        catch (InputMismatchException exception) {
            printStream.flush();
            return false;
        }

        return true;
    }

    @Override
    public boolean procCommand(GameState gameState, Player player) {
        System.out.println("processing STATUS command: COMPLETE-MISSION");
        return true;
    }
}

package base.controller;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PhaseController {

    private HashMap<String, PlayerStrategyCommand> strategyCommandHashMap;
    private HashMap<String, PlayerActionCommand> actionCommandHashMap;
    private HashMap<String, PlayerStatusCommand> statusCommandHashMap;

    public PhaseController() {
        strategyCommandHashMap = new HashMap<>();
        strategyCommandHashMap.put("pick", new CPlayerStrategyPick());

        actionCommandHashMap = new HashMap<>();
        actionCommandHashMap.put("move", new PlayerActionMove());

        statusCommandHashMap = new HashMap<>();
        statusCommandHashMap.put("complete-mission", new CPlayerStatusCompleteMission());
    }

    public PlayerStrategyCommand getStrategyCommand(String commandStr) {
        return strategyCommandHashMap.get(commandStr);
    }

    public PlayerActionCommand getActionCommand(String commandStr) {
        return actionCommandHashMap.get(commandStr);
    }

    public PlayerStatusCommand getStatusCommand(String commandStr) {
        return statusCommandHashMap.get(commandStr);
    }

    public interface PlayerCommand {
        Boolean inputCommand(PrintStream printStream, Scanner inputScanner);
        void procCommand();
    }

    public interface PlayerStrategyCommand extends PlayerCommand {
    }

    public interface PlayerActionCommand extends PlayerCommand {
    }

    public interface PlayerStatusCommand extends PlayerCommand {
    }

    public static class PlayerActionMove implements PlayerActionCommand {

        public Integer shipIdx = 0;
        public Integer spaceIdx = 0;

        public PlayerActionMove() {
            this.shipIdx = 0;
            this.spaceIdx = 0;
        }

        public PlayerActionMove(Integer shipIdx, Integer spaceIdx) {
            this.shipIdx = shipIdx;
            this.spaceIdx = spaceIdx;
        }

        @Override
        public Boolean inputCommand(PrintStream printStream, Scanner inputScanner) {
            try {
                printStream.println("enter ship number:");
                shipIdx = inputScanner.nextInt();
                printStream.println("enter destination space number:");
                spaceIdx = inputScanner.nextInt();
            } catch (InputMismatchException exception) {
                printStream.flush();
                return false;
            }

            return true;
        }

        @Override
        public void procCommand() {
            System.out.println("processing ACTION command: MOVE");
        }
    }

    public static class CPlayerStrategyPick implements PlayerStrategyCommand {

        public Integer strategyIdx = 0;

        public CPlayerStrategyPick() {
            this.strategyIdx = 0;
        }

        public CPlayerStrategyPick(Integer strategyIdx) {
            this.strategyIdx = strategyIdx;
        }

        @Override
        public Boolean inputCommand(PrintStream printStream, Scanner inputScanner) {
            try {
                printStream.println("enter strategy number:");
                strategyIdx = inputScanner.nextInt();
            } catch (InputMismatchException exception) {
                printStream.flush();
                return false;
            }

            return true;
        }

        @Override
        public void procCommand() {
            System.out.println("processing STRATEGY command: PICK");
        }
    }

    public static class CPlayerStatusCompleteMission implements PlayerStatusCommand {

        public Integer missionIdx = 0;

        public CPlayerStatusCompleteMission() {
            this.missionIdx = 0;
        }

        public CPlayerStatusCompleteMission(Integer missionIdx) {
            this.missionIdx = missionIdx;
        }

        @Override
        public Boolean inputCommand(PrintStream printStream, Scanner inputScanner) {
            try {
                printStream.println("enter mission number:");
                missionIdx = inputScanner.nextInt();
            } catch (InputMismatchException exception) {
                printStream.flush();
                return false;
            }

            return true;
        }

        @Override
        public void procCommand() {
            System.out.println("processing STATUS command: COMPLETE-MISSION");
        }
    }
}

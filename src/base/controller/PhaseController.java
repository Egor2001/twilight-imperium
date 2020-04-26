package base.controller;

import java.io.PrintStream;
import java.util.ArrayList;
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
        actionCommandHashMap.put("add-ship", new PlayerActionAddShip());

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

    public static class PlayerActionAddShip implements PlayerActionCommand {

        public String shipName = null;
        public HierarchyController.GameObjectTarget spaceTarget = null;

        public PlayerActionAddShip() {
            this.shipName = null;
            this.spaceTarget = null;
        }

        public PlayerActionAddShip(String shipName,
                                   HierarchyController.GameObjectTarget spaceTarget) {
            this.shipName = shipName;
            this.spaceTarget = spaceTarget;
        }

        @Override
        public Boolean inputCommand(PrintStream printStream, Scanner inputScanner) {
            try {
                printStream.println("enter ship name:");
                shipName = inputScanner.next();
                printStream.println("enter destination space target:");
                spaceTarget = HierarchyController.parseTarget(inputScanner.next());
            } catch (InputMismatchException exception) {
                printStream.flush();
                return false;
            }

            return true;
        }

        @Override
        public void procCommand() {
            System.out.println("processing ACTION command: ADD_SHIP");
        }
    }

    public static class PlayerActionMove implements PlayerActionCommand {

        public HierarchyController.GameObjectTarget shipTarget = null;
        public ArrayList<HierarchyController.GameObjectTarget> spaceTargetList = null;

        public PlayerActionMove() {
            this.shipTarget = null;
            this.spaceTargetList = new ArrayList<>();
        }

        public PlayerActionMove(HierarchyController.GameObjectTarget shipTarget,
                                ArrayList<HierarchyController.GameObjectTarget> spaceTargetList) {
            this.shipTarget = shipTarget;
            this.spaceTargetList = spaceTargetList;
        }

        @Override
        public Boolean inputCommand(PrintStream printStream, Scanner inputScanner) {
            try {
                printStream.println("enter ship target:");
                shipTarget = HierarchyController.parseTarget(inputScanner.next());
                printStream.println("enter path length:");
                int pathLength = inputScanner.nextInt();
                while (pathLength-- > 0) {
                    printStream.println("enter next space target:");
                    spaceTargetList.add(HierarchyController.parseTarget(inputScanner.next()));
                }
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

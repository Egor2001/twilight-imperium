package base.controller;

import ArmyUnits.Ships.Ship;
import ArmyUnits.Unit;
import base.model.GameState;
import base.model.Player;
import tile.Board;
import tile.Space;
import tile.TileObject;

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
        actionCommandHashMap.put("add-unit", new PlayerActionAddUnit());

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
        boolean procCommand(GameState gameState, Player player);
    }

    public interface PlayerStrategyCommand extends PlayerCommand {
    }

    public interface PlayerActionCommand extends PlayerCommand {
    }

    public interface PlayerStatusCommand extends PlayerCommand {
    }

    public static class PlayerActionAddUnit implements PlayerActionCommand {

        public String unitName = null;
        public HierarchyController.GameObjectTarget tileObjectTarget = null;

        public PlayerActionAddUnit() {
            this.unitName = null;
            this.tileObjectTarget = null;
        }

        public PlayerActionAddUnit(String unitName,
                                   HierarchyController.GameObjectTarget tileObjectTarget) {
            this.unitName = unitName;
            this.tileObjectTarget = tileObjectTarget;
        }

        @Override
        public Boolean inputCommand(PrintStream printStream, Scanner inputScanner) {
            try {
                printStream.println("enter ship name:");
                unitName = inputScanner.next();
                printStream.println("enter destination space target:");
                tileObjectTarget = HierarchyController.parseTarget(inputScanner.next());
            } catch (InputMismatchException exception) {
                printStream.flush();
                return false;
            }

            return true;
        }

        @Override
        public boolean procCommand(GameState gameState, Player player) {
            System.out.println("processing ACTION command: ADD_SHIP");

            try {
                TileObject tileObject = (TileObject) gameState.getBoard().getObject(tileObjectTarget);
                Unit unit = player.addUnit(unitName);

                gameState.getTileArmyController().add(unit, tileObject);
            }
            catch (Exception exception) {
                System.out.println(exception.getMessage());
                return false;
            }

            return true;
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
        public boolean procCommand(GameState gameState, Player player) {
            System.out.println("processing ACTION command: MOVE");

            try {
                Ship ship = (Ship) player.getObject(shipTarget);
                ArrayList<TileObject> tileObjectList = new ArrayList<>();

                Board board = gameState.getBoard();
                for (HierarchyController.GameObjectTarget tileObjectTarget : spaceTargetList) {
                    tileObjectList.add((TileObject) board.getObject(tileObjectTarget));
                }

                gameState.getTileArmyController().move(ship, tileObjectList);
            }
            catch (Exception exception) {
                System.out.print("ERROR OCCURED: ");
                System.out.println(exception.getMessage());
                return false;
            }

            return true;
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
}

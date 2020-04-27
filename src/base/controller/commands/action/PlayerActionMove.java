package base.controller.commands.action;

import ArmyUnits.Ships.Ship;
import base.controller.HierarchyController;
import base.model.GameState;
import base.model.Player;
import tile.Board;
import tile.TileObject;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerActionMove implements PlayerActionCommand {

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
    public boolean inputCommand(PrintStream printStream, Scanner inputScanner) {
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

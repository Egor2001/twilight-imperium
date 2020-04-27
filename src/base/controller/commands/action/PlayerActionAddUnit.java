package base.controller.commands.action;

import ArmyUnits.Unit;
import base.controller.HierarchyController;
import base.model.GameState;
import base.model.Player;
import tile.TileObject;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerActionAddUnit implements PlayerActionCommand {

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
    public boolean inputCommand(PrintStream printStream, Scanner inputScanner) {
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

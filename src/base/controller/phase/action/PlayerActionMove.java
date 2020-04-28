package base.controller.phase.action;

import ArmyUnits.Ships.Ship;
import base.controller.CommandResponse;
import base.controller.HierarchyController;
import base.controller.CommandRequestable;
import base.model.GameState;
import base.model.Player;
import tile.Board;
import tile.TileObject;

import java.util.ArrayList;

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
    public boolean inputCommand(CommandRequestable userInterface) {
        shipTarget = userInterface.requestTarget("ship");
        int pathLength = userInterface.requestNumber("path length");
        while (pathLength-- > 0) {
            HierarchyController.GameObjectTarget nextTarget = userInterface.requestTarget("next");
            spaceTargetList.add(nextTarget);
        }

        return true;
    }

    @Override
    public CommandResponse execute(GameState gameState, Player player) {
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
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

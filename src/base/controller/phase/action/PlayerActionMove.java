package base.controller.phase.action;

import base.user.GameObjectTarget;
import player.units.Ships.Ship;
import base.controller.CommandResponse;
import base.user.CommandRequestable;
import base.model.GameState;
import player.Player;
import board.Board;
import board.TileObject;

import java.util.ArrayList;

public class PlayerActionMove implements PlayerActionCommand {

    public GameObjectTarget shipTarget = null;
    public ArrayList<GameObjectTarget> spaceTargetList = null;

    public PlayerActionMove() {
        this.shipTarget = null;
        this.spaceTargetList = new ArrayList<>();
    }

    public PlayerActionMove(GameObjectTarget shipTarget,
                            ArrayList<GameObjectTarget> spaceTargetList) {
        this.shipTarget = shipTarget;
        this.spaceTargetList = spaceTargetList;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        shipTarget = userInterface.requestTarget("ship");
        int pathLength = userInterface.requestNumber("path length");
        while (pathLength-- > 0) {
            GameObjectTarget nextTarget = userInterface.requestTarget("next");
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
            for (GameObjectTarget tileObjectTarget : spaceTargetList) {
                tileObjectList.add((TileObject) board.getObject(tileObjectTarget));
            }

            gameState.getTileArmyManager().move(ship, tileObjectList);
        }
        catch (Exception exception) {
            System.out.print("ERROR OCCURED: ");
            System.out.println(exception.getMessage());
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

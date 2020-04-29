package base.controller.tactic;

import base.controller.CommandResponse;
import base.model.GameState;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import board.Tile;
import board.TileObject;
import player.Player;
import player.units.Unit;

import java.util.ArrayList;

public class PlayerTacticAddWayCommand implements PlayerTacticCommand {
    private MoveState moveState;
    private ArrayList<GameObjectTarget> waysTarget;
    private ArrayList<GameObjectTarget> unitsTarget;

    PlayerTacticAddWayCommand() {
        moveState = null;
        waysTarget = new ArrayList<>();
        unitsTarget = new ArrayList<>();
    }

    @Override
    public void setMoveState(MoveState moveState) {
        this.moveState = moveState;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        int numUnits = userInterface.requestNumber("number units to move");
        for (int i = 0; i < numUnits; ++i) {
            unitsTarget.add(userInterface.requestTarget("unit object"));
        }

        int numTiles = userInterface.requestNumber("number tiles in way");
        for (int i = 0; i < numUnits; ++i) {
            unitsTarget.add(userInterface.requestTarget("tile object"));
        }

        return true;
    }

    @Override
    public CommandResponse execute(GameState gameState, Player player) {
        System.out.println("processing TACTIC command: ADD_WAY");

        boolean error = false;
        ArrayList<Unit> units = new ArrayList<>();
        ArrayList<TileObject> tileObjects = new ArrayList<>();

        for (GameObjectTarget unitTarget: unitsTarget){
            try {
                units.add((Unit) player.getObject(unitTarget));
            }
            catch (Exception exception) {
                System.out.println(exception.getMessage());
                error = true;
            }
        }

        for (GameObjectTarget tileObjectTarget: waysTarget){
            try {
                tileObjects.add((TileObject) gameState.getBoard().getObject(tileObjectTarget));
            }
            catch (Exception exception) {
                System.out.println(exception.getMessage());
                error = true;
            }
        }

        try {
            moveState.addWay(units, tileObjects);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            error = true;
        }

        if (error) {
            return CommandResponse.DECLINED;
        }
        return CommandResponse.ACCEPTED;
    }
}

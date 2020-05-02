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

public class PlayerTacticAddWayCommand extends PlayerTacticCommand {
    private ArrayList<GameObjectTarget> waysTarget;
    private ArrayList<GameObjectTarget> unitsTarget;

    PlayerTacticAddWayCommand(MoveController controller) {
        super(controller);
        waysTarget = new ArrayList<>();
        unitsTarget = new ArrayList<>();
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        int numUnits = userInterface.requestNumber("units to move");
        for (int i = 0; i < numUnits; ++i) {
            unitsTarget.add(userInterface.requestTarget("unit object"));
        }

        int numTiles = userInterface.requestNumber("tiles in way");
        for (int i = 0; i < numTiles; ++i) {
            waysTarget.add(userInterface.requestTarget("tile object"));
        }

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        GameState gameState = ((MoveController) getController()).getGameState();

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
                //exception.printStackTrace();
                error = true;
            }
        }

        for (GameObjectTarget tileObjectTarget: waysTarget){
            try {
                tileObjects.add((TileObject) gameState.getBoard().getObject(tileObjectTarget));
            }
            catch (Exception exception) {
                System.out.println(exception.getMessage());
                //exception.printStackTrace();
                error = true;
            }
        }

        try {
            ((MoveController) controller).getMoveState().addWay(units, tileObjects);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            //exception.printStackTrace();
            error = true;
        }

        if (error) {
            return CommandResponse.DECLINED;
        }
        return CommandResponse.ACCEPTED;
    }
}

package base.controller.phase.action;

import ArmyUnits.Unit;
import base.controller.CommandResponse;
import base.controller.HierarchyController;
import base.controller.CommandRequestable;
import base.model.GameState;
import base.model.Player;
import tile.TileObject;

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
    public boolean inputCommand(CommandRequestable userInterface) {
        unitName = userInterface.requestName("unit");
        tileObjectTarget = userInterface.requestTarget("tile object");

        return true;
    }

    @Override
    public CommandResponse execute(GameState gameState, Player player) {
        System.out.println("processing ACTION command: ADD_SHIP");

        try {
            TileObject tileObject = (TileObject) gameState.getBoard().getObject(tileObjectTarget);
            Unit unit = player.addUnit(unitName);

            gameState.getTileArmyController().add(unit, tileObject);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

package base.controller.phase.action;

import base.controller.AbstractCommand;
import base.user.GameObjectTarget;
import player.units.Unit;
import base.controller.CommandResponse;
import base.user.CommandRequestable;
import base.model.GameState;
import player.Player;
import board.TileObject;

public class PlayerActionAddUnit extends PlayerActionCommand {

    public String unitName = null;
    public GameObjectTarget tileObjectTarget = null;

    public PlayerActionAddUnit(ActionPhaseController controller) {
        super(controller);
        this.unitName = null;
        this.tileObjectTarget = null;
    }

    public PlayerActionAddUnit(ActionPhaseController controller,
                               String unitName, GameObjectTarget tileObjectTarget) {
        this(controller);
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
    public CommandResponse execute(Player player) {
        GameState gameState = ((ActionPhaseController) getController()).getGameState();

        try {
            TileObject tileObject = (TileObject) gameState.getBoard().getObject(tileObjectTarget);
            Unit unit = player.addUnit(unitName);

            gameState.getTileArmyManager().add(unit, tileObject);
        }
        catch (Exception exception) {
            getController().getUserInterface().reportError(exception.getMessage());
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

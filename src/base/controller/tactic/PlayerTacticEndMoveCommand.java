package base.controller.tactic;

import base.controller.CommandResponse;
import base.model.GameState;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import board.TileObject;
import player.Player;
import player.units.Ships.Ship;
import player.units.Unit;

import java.util.ArrayList;

public class PlayerTacticEndMoveCommand extends PlayerTacticCommand {
    public PlayerTacticEndMoveCommand(MoveController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        GameState gameState = ((MoveController) getController()).getGameState();
        System.out.println("processing TACTIC command: END_MOVE");

        boolean error = false;
        ArrayList<Unit> units = ((MoveController) controller).getMoveState().getUnitList();

        for (Unit unit: units) {
            try {
                gameState.getTileArmyManager().move((Ship) unit,
                        ((MoveController) controller).getMoveState().getInternalUnits(unit),
                        ((MoveController) controller).getMoveState().getWay(unit));
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                error = true;
            }
        }

        if (error) {
            return CommandResponse.DECLINED;
        }
        return CommandResponse.END_EVENT;
    }
}

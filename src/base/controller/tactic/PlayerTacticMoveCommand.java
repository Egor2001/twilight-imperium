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

public class PlayerTacticMoveCommand implements PlayerTacticCommand {
    private MoveState moveState;

    PlayerTacticMoveCommand() {
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
        return true;
    }

    @Override
    public CommandResponse execute(GameState gameState, Player player) {
        System.out.println("processing TACTIC command: MOVE");

        boolean error = false;
        ArrayList<Unit> units = moveState.getUnitList();

        for (Unit unit: units) {
            try {
                gameState.getTileArmyManager().move((Ship) unit, moveState.getInternalUnits(unit), moveState.getWay(unit));
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

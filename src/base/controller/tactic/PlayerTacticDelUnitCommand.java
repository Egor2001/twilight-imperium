package base.controller.tactic;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import player.Player;
import player.units.Unit;

import java.util.ArrayList;

public class PlayerTacticDelUnitCommand extends PlayerTacticCommand {
    private MoveState moveState;
    private GameObjectTarget unitTarget;

    PlayerTacticDelUnitCommand(MoveController controller) {
        super(controller);
        moveState = null;
        unitTarget = null;
    }

    @Override
    public void setMoveState(MoveState moveState) {
        this.moveState = moveState;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        unitTarget = userInterface.requestTarget("del unit");

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        System.out.println("processing TACTIC command: DEL_UNIT");

        try {
            Unit unit = (Unit) player.getObject(unitTarget);
            moveState.delUnit(unit);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

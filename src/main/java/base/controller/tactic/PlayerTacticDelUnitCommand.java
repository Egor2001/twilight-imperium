package base.controller.tactic;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import base.view.MessageString;
import player.Player;
import player.units.Unit;

import java.util.ArrayList;

public class PlayerTacticDelUnitCommand extends PlayerTacticCommand {
    private GameObjectTarget unitTarget;

    PlayerTacticDelUnitCommand(MoveController controller) {
        super(controller);
        unitTarget = null;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        unitTarget = userInterface.requestTarget("del unit");

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        controller.getUserInterface().displayView(new MessageString("processing TACTIC command: DEL_UNIT"));

        try {
            Unit unit = (Unit) player.getObject(unitTarget);
            ((MoveController) controller).getMoveState().delUnit(unit);
        } catch (Exception exception) {
            controller.getUserInterface().displayView(new MessageString(exception.getMessage()));
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

package base.controller.tactic;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import base.view.MessageString;
import player.Player;
import player.units.Unit;

public class PlayerTacticViewUnitCommand extends PlayerTacticCommand {
    private GameObjectTarget unitTarget;

    PlayerTacticViewUnitCommand(MoveController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        unitTarget = userInterface.requestTarget("unit for view");
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        controller.getUserInterface().displayView(new MessageString("processing TACTIC command: VIEW_UNIT"));
        Unit unit = (Unit) player.getObject(unitTarget);

        controller.getUserInterface().displayView(
                new MessageString(((MoveController) controller).getMoveState().getUnitInfo(unit, "")));
        return CommandResponse.ACCEPTED;
    }
}

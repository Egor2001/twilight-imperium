package base.controller.tactic;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
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
        Unit unit = (Unit) player.getObject(unitTarget);

        System.out.println(((MoveController) controller).getMoveState().getUnitInfo(unit, ""));
        return CommandResponse.ACCEPTED;
    }
}

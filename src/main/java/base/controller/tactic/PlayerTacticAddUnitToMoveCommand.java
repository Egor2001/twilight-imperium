package base.controller.tactic;

import base.controller.CommandResponse;
import base.model.GameState;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import base.view.MessageString;
import player.Player;
import player.units.Unit;

import java.util.ArrayList;

public class PlayerTacticAddUnitToMoveCommand extends PlayerTacticCommand {
    private ArrayList<GameObjectTarget> unitTargets;

    PlayerTacticAddUnitToMoveCommand(MoveController controller) {
        super(controller);
        unitTargets = new ArrayList<>();
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        int numUnits = userInterface.requestNumber("units to move");
        for (int i = 0; i < numUnits; ++i) {
            unitTargets.add(userInterface.requestTarget("unit object"));
        }

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        controller.getUserInterface().displayView(new MessageString("processing TACTIC command: ADD_UNIT"));

        boolean error = false;
        ArrayList<Unit> units = new ArrayList<>();

        for (GameObjectTarget unitTarget: unitTargets){
            try {
                units.add((Unit) player.getObject(unitTarget));
            }
            catch (Exception exception) {
                controller.getUserInterface().displayView(new MessageString(exception.getMessage()));
                error = true;
            }
        }

        try {
            ((MoveController) controller).getMoveState().addUnit(units);
        } catch (Exception exception) {
            controller.getUserInterface().displayView(new MessageString(exception.getMessage()));
            error = true;
        }

        if (error) {
            return CommandResponse.DECLINED;
        }
        return CommandResponse.ACCEPTED;
    }
}

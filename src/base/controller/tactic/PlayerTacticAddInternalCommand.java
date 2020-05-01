package base.controller.tactic;

import base.controller.CommandResponse;
import base.model.GameState;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import player.Player;
import player.units.Unit;

import java.util.ArrayList;

public class PlayerTacticAddInternalCommand extends PlayerTacticCommand {
    private GameObjectTarget unitTarget;
    private ArrayList<GameObjectTarget> unitsInternalTarget;

    PlayerTacticAddInternalCommand(MoveController controller) {
        super(controller);
        unitTarget = null;
        unitsInternalTarget = new ArrayList<>();
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        unitTarget = userInterface.requestTarget("unit for load");

        int numUnits = userInterface.requestNumber("number units to internal");
        for (int i = 0; i < numUnits; ++i) {
            unitsInternalTarget.add(userInterface.requestTarget("unit object to internal"));
        }

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        System.out.println("processing TACTIC command: ADD_INTERNAL_UNITS");

        boolean error = false;
        ArrayList<Unit> unitsInternal = new ArrayList<>();
        Unit unit = (Unit) player.getObject(unitTarget);

        for (GameObjectTarget unitInternalTarget: unitsInternalTarget){
            try {
                unitsInternal.add((Unit) player.getObject(unitInternalTarget));
            }
            catch (Exception exception) {
                System.out.println(exception.getMessage());
                error = true;
            }
        }

        try {
            ((MoveController) controller).getMoveState().addUnitsInsideUnit(unit, unitsInternal);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            error = true;
        }

        if (error) {
            return CommandResponse.DECLINED;
        }
        return CommandResponse.ACCEPTED;
    }
}

package base.controller.tactic;

import base.controller.CommandResponse;
import base.model.GameState;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import player.Player;
import player.units.Unit;

import java.util.ArrayList;

public class PlayerTacticAddUnitToMoveCommand implements PlayerTacticCommand {
    private MoveState moveState;
    private ArrayList<GameObjectTarget> unitTargets;

    PlayerTacticAddUnitToMoveCommand() {
        moveState = null;
        unitTargets = new ArrayList<>();
    }

    @Override
    public void setMoveState(MoveState moveState) {
        this.moveState = moveState;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        int numUnits = userInterface.requestNumber("number units to move");
        for (int i = 0; i < numUnits; ++i) {
            unitTargets.add(userInterface.requestTarget("unit object"));
        }

        return true;
    }

    @Override
    public CommandResponse execute(GameState gameState, Player player) {
        System.out.println("processing TACTIC command: ADD_UNIT");

        boolean error = false;
        ArrayList<Unit> units = new ArrayList<>();

        for (GameObjectTarget unitTarget: unitTargets){
            try {
                units.add((Unit) player.getObject(unitTarget));
            }
            catch (Exception exception) {
                System.out.println(exception.getMessage());
                error = true;
            }
        }

        try {
            moveState.addUnit(units);
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

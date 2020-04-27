package base.controller.phase;

import base.controller.CommandRequestable;
import base.model.GameState;
import base.model.Player;

public class PlayerStatusCompleteMission implements PlayerStatusCommand {

    public int missionIdx = 0;

    public PlayerStatusCompleteMission() {
        this.missionIdx = 0;
    }

    public PlayerStatusCompleteMission(int missionIdx) {
        this.missionIdx = missionIdx;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        missionIdx = userInterface.requestNumber("mission index");
        return true;
    }

    @Override
    public boolean execute(GameState gameState, Player player) {
        System.out.println("processing STATUS command: COMPLETE-MISSION");
        return true;
    }
}

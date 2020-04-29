package base.controller.phase.status;

import base.user.CommandRequestable;
import base.controller.CommandResponse;
import base.model.GameState;
import player.Player;

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
    public CommandResponse execute(GameState gameState, Player player) {
        System.out.println("processing STATUS command: COMPLETE-MISSION");
        return CommandResponse.ACCEPTED;
    }
}

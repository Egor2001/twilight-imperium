package base.controller.phase.status;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.user.CommandRequestable;
import base.controller.CommandResponse;
import base.model.GameState;
import player.Player;

public class PlayerStatusCompleteMission extends PlayerStatusCommand {

    public int missionIdx = 0;

    public PlayerStatusCompleteMission(AbstractController controller) {
        super(controller);
        this.missionIdx = 0;
    }

    public PlayerStatusCompleteMission(AbstractController controller, int missionIdx) {
        this(controller);
        this.missionIdx = missionIdx;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        missionIdx = userInterface.requestNumber("mission index");
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        return CommandResponse.ACCEPTED;
    }
}

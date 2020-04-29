package base.controller.tactic;

import base.controller.PlayerCommand;

public interface PlayerTacticCommand extends PlayerCommand {
    void setMoveState(MoveState moveState);
}

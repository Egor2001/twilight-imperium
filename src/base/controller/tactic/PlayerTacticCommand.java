package base.controller.tactic;

import base.controller.AbstractCommand;

public abstract class PlayerTacticCommand extends AbstractCommand {
    public PlayerTacticCommand(MoveController controller) {
        super(controller);
    }

    public abstract void setMoveState(MoveState moveState);
}

package base.controller.invasion;

import base.controller.AbstractCommand;

public abstract class PlayerInvasionCommand extends AbstractCommand {
    public PlayerInvasionCommand(InvasionController controller) {
        super(controller);
    }
}
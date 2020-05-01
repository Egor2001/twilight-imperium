package base.controller.phase.action;

import base.controller.AbstractCommand;
import base.controller.AbstractController;

public abstract class PlayerActionCommand extends AbstractCommand {
    public PlayerActionCommand(AbstractController controller) {
        super(controller);
    }
}

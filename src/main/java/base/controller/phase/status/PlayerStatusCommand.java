package base.controller.phase.status;

import base.controller.AbstractCommand;
import base.controller.AbstractController;

public abstract class PlayerStatusCommand extends AbstractCommand {
    public PlayerStatusCommand(AbstractController controller) {
        super(controller);
    }
}

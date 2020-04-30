package base.controller.phase.strategy;

import base.controller.AbstractCommand;
import base.controller.AbstractController;

public abstract class PlayerStrategyCommand extends AbstractCommand {
    public PlayerStrategyCommand(AbstractController controller) {
        super(controller);
    }
}

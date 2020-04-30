package player.cards;

import base.controller.AbstractCommand;
import base.controller.global.GlobalCommandController;

public abstract class AbstractCard extends AbstractCommand {

    private CardManager manager;

    public AbstractCard(GlobalCommandController controller, CardManager manager) {
        super(controller);
        this.manager = manager;
    }

    public abstract void execute();
}

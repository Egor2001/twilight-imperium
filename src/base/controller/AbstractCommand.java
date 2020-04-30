package base.controller;

import base.user.CommandRequestable;
import player.Player;

public abstract class AbstractCommand {

    protected AbstractController controller;

    public AbstractCommand(AbstractController controller) {
        this.controller = controller;
    }

    public abstract boolean inputCommand(CommandRequestable userInterface);
    public abstract CommandResponse execute(Player player);

    protected AbstractController getController() {
        return controller;
    }
}

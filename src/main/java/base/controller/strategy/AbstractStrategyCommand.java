package base.controller.strategy;

import base.controller.AbstractCommand;
import base.controller.CommandResponse;
import base.user.CommandRequestable;
import player.Player;

public abstract class AbstractStrategyCommand extends AbstractCommand {

    public AbstractStrategyCommand(AbstractStrategy strategy) {
        super(strategy);
    }

    @Override
    public abstract boolean inputCommand(CommandRequestable userInterface);

    @Override
    public abstract CommandResponse execute(Player player);
}

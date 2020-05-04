package base.controller.strategy;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import player.Player;

public class StrategyPass extends AbstractStrategyCommand {
    public StrategyPass(AbstractStrategy strategy) {
        super(strategy);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        return CommandResponse.ACCEPTED;
    }
}

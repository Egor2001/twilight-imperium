package base.controller.phase.action;

import base.controller.AbstractController;
import base.controller.CommandResponse;
import base.controller.strategy.AbstractStrategy;
import base.user.CommandRequestable;
import player.Player;

public class PlayerActionStrategy extends PlayerActionCommand {

    public PlayerActionStrategy(AbstractController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        AbstractStrategy strategy = getController().getGameState().getPlayerStrategyManager().get(player);
        if (strategy == null) {
            return CommandResponse.DECLINED;
        }

        return strategy.start();
    }
}

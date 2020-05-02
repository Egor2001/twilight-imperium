package base.controller.phase.strategy;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.user.CommandRequestable;
import base.controller.CommandResponse;
import base.model.GameState;
import player.Player;

public class PlayerStrategyPick extends PlayerStrategyCommand {

    public int strategyIdx = 0;

    public PlayerStrategyPick(AbstractController controller) {
        super(controller);
        this.strategyIdx = 0;
    }

    public PlayerStrategyPick(AbstractController controller, int strategyIdx) {
        this(controller);
        this.strategyIdx = strategyIdx;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        strategyIdx = userInterface.requestNumber("strategy index");
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        return CommandResponse.ACCEPTED;
    }
}

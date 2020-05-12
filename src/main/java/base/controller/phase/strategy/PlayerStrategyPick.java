package base.controller.phase.strategy;

import base.controller.AbstractController;
import base.controller.strategy.AbstractStrategy;
import base.user.CommandRequestable;
import base.controller.CommandResponse;
import base.view.Viewable;
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
        Viewable strategiesView = ((StrategyPhaseController) getController()).getStrategiesView();
        //userInterface.displayView(strategiesView);
        strategyIdx = userInterface.requestNumber("strategy index") - 1;
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        StrategyPhaseController controller = ((StrategyPhaseController) getController());

        try {
            AbstractStrategy strategy = controller.getStrategy(player, strategyIdx);
            controller.getGameState().getPlayerStrategyManager().pickNew(player, strategy);
        }
        catch (IllegalArgumentException exception) {
            getController().getUserInterface().reportError(exception.getMessage());
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

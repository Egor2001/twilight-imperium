package base.controller.phase.strategy;

import base.user.CommandRequestable;
import base.controller.CommandResponse;
import base.model.GameState;
import player.Player;

public class PlayerStrategyPick implements PlayerStrategyCommand {

    public int strategyIdx = 0;

    public PlayerStrategyPick() {
        this.strategyIdx = 0;
    }

    public PlayerStrategyPick(int strategyIdx) {
        this.strategyIdx = strategyIdx;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        strategyIdx = userInterface.requestNumber("strategy index");
        return true;
    }

    @Override
    public CommandResponse execute(GameState gameState, Player player) {
        System.out.println("processing STRATEGY command: PICK");
        return CommandResponse.ACCEPTED;
    }
}

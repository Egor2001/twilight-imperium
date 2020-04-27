package base.controller.phase;

import base.controller.CommandRequestable;
import base.model.GameState;
import base.model.Player;

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
    public boolean execute(GameState gameState, Player player) {
        System.out.println("processing STRATEGY command: PICK");
        return true;
    }
}

package base.controller.fight;

import base.controller.CommandResponse;
import base.controller.PlayerCommand;
import base.model.GameState;
import base.user.CommandRequestable;
import player.Player;

public class PlayerCombatRetreat implements PlayerCommand {

    private SpaceCombatController controller;

    public PlayerCombatRetreat(SpaceCombatController controller) {
        this.controller = controller;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(GameState gameState, Player player) {
        boolean canRetreat = controller.announceRetreat(player);
        return (canRetreat ? CommandResponse.END_EVENT : CommandResponse.DECLINED);
    }
}

package base.controller.fight;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import player.Player;

public class PlayerCombatRetreat extends PlayerCombatCommand {

    public PlayerCombatRetreat(SpaceCombatController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        boolean canRetreat = ((SpaceCombatController) getController()).retreat(player);
        return (canRetreat ? CommandResponse.END_EVENT : CommandResponse.DECLINED);
    }
}

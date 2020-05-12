package base.controller.combat;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import board.Space;
import player.Player;

public class PlayerCombatBarrage extends PlayerCombatCommand {

    public PlayerCombatBarrage(SpaceCombatController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        SpaceCombatController.CombatPhase phase = ((SpaceCombatController) getController()).getCombatPhase();
        if (phase != SpaceCombatController.CombatPhase.COMBAT_BARRAGE) {
            getController().getUserInterface().reportError("can't assign hits in " + phase.toString() + " phase");
            return CommandResponse.DECLINED;
        }

        int result = ((SpaceCombatController) getController()).barrage(player);

        return CommandResponse.ACCEPTED;
    }
}

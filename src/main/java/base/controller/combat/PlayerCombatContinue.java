package base.controller.combat;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import player.Player;

public class PlayerCombatContinue extends PlayerCombatCommand {

    public PlayerCombatContinue(SpaceCombatController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        SpaceCombatController.CombatPhase phase = ((SpaceCombatController) getController()).getCombatPhase();
        if (phase != SpaceCombatController.CombatPhase.COMBAT_RETREAT &&
                phase != SpaceCombatController.CombatPhase.COMBAT_BARRAGE) {
            getController().getUserInterface().reportError("can't continue in " + phase.toString() + " phase");
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

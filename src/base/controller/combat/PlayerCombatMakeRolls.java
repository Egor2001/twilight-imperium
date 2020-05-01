package base.controller.combat;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import board.Space;
import player.Player;

public class PlayerCombatMakeRolls extends PlayerCombatCommand {

    public PlayerCombatMakeRolls(SpaceCombatController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return false;
    }

    @Override
    public CommandResponse execute(Player player) {
        SpaceCombatController.CombatPhase phase = ((SpaceCombatController) getController()).getCombatPhase();
        if (phase != SpaceCombatController.CombatPhase.COMBAT_MAKE_ROLLS) {
            getController().getUserInterface().reportError("can't make rolls in " + phase.toString() + " phase");
            return CommandResponse.DECLINED;
        }

        ((SpaceCombatController) getController()).makeRolls(player);
        return CommandResponse.ACCEPTED;
    }
}

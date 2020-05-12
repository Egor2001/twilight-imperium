package base.controller.invasion;

import base.controller.CommandResponse;
import base.controller.combat.SpaceCombatController;
import base.user.CommandRequestable;
import player.Player;

public class PlayerInvasionEndBombarding extends PlayerInvasionCommand  {
    public PlayerInvasionEndBombarding(InvasionController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        return CommandResponse.END_EVENT;
    }
}

package base.controller.invasion;

import base.controller.CommandResponse;
import base.controller.combat.SpaceCombatController;
import base.user.CommandRequestable;
import player.Player;

public class PlayerInvasionDefend extends PlayerInvasionCommand  {
    public PlayerInvasionDefend(InvasionController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return false;
    }

    @Override
    public CommandResponse execute(Player player) {
        return null;
    }
}

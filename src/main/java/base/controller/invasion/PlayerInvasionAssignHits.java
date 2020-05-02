package base.controller.invasion;

import base.controller.CommandResponse;

import base.user.CommandRequestable;
import player.Player;

public class PlayerInvasionAssignHits extends PlayerInvasionCommand  {
    public PlayerInvasionAssignHits(InvasionController controller) {
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

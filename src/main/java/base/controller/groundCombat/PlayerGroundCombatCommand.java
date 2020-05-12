package base.controller.groundCombat;

import base.controller.AbstractCommand;

public abstract class PlayerGroundCombatCommand extends AbstractCommand {
    public PlayerGroundCombatCommand(GroundCombatController controller) {
        super(controller);
    }
}

package base.controller.combat;

import base.controller.AbstractCommand;

public abstract class PlayerCombatCommand extends AbstractCommand {

    public PlayerCombatCommand(SpaceCombatController controller) {
        super(controller);
    }
}

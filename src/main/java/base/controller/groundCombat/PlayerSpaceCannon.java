package base.controller.groundCombat;

import base.controller.CommandResponse;
import base.controller.tactic.MoveController;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import base.view.MessageString;
import player.Player;
import player.units.Structures.PDS;

import javax.swing.plaf.multi.MultiSeparatorUI;
import java.util.ArrayList;

public class PlayerSpaceCannon extends PlayerGroundCombatCommand {
    private ArrayList<GameObjectTarget> pdsTargets;

    public PlayerSpaceCannon(GroundCombatController controller) {
        super(controller);
        pdsTargets = new ArrayList<>();
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        int numPDS = userInterface.requestNumber("PDS to space cannon");
        for (int i = 0; i < numPDS; ++i) {
            pdsTargets.add(userInterface.requestTarget("PDS to space cannon"));
        }

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        controller.getUserInterface().displayView(new MessageString("processing GROUND COMBAT command: SPACE CANNON"));

        ArrayList<PDS> pds = ((GroundCombatController) controller).getPdsForSpaceCannon();
        boolean error = false;
        for (GameObjectTarget pdsTarget: pdsTargets) {
            try {
                PDS addPDS = (PDS) player.getObject(pdsTarget);
                if (!pds.contains(addPDS)) {
                    pds.add(addPDS);
                } else {
                    controller.getUserInterface().displayView(new MessageString("This pds is already use"));
                }
            } catch (Exception exception) {
                controller.getUserInterface().displayView(new MessageString(exception.getMessage()));
                error = true;
            }
        }

        if (error) {
            return CommandResponse.DECLINED;
        }
        return CommandResponse.ACCEPTED;
    }
}

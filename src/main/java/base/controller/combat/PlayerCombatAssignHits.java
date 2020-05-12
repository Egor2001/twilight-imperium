package base.controller.combat;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import player.Player;

import java.util.ArrayList;

public class PlayerCombatAssignHits extends PlayerCombatCommand {

    private ArrayList<Integer> hitIdxList;

    public PlayerCombatAssignHits(SpaceCombatController controller) {
        super(controller);
        this.controller = controller;
        this.hitIdxList = new ArrayList<>();
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        hitIdxList.clear();

        int hitValue = ((SpaceCombatController) controller).getAssignValue();
        for (int hitIdx = 0; hitIdx != hitValue; ++hitIdx) {
            hitIdxList.add(userInterface.requestNumber(hitIdx + "'s hit ship idx"));
        }

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        SpaceCombatController.CombatPhase phase = ((SpaceCombatController) getController()).getCombatPhase();
        if (phase != SpaceCombatController.CombatPhase.COMBAT_ASSIGN_HITS) {
            getController().getUserInterface().reportError("can't assign hits in " + phase.toString() + " phase");
            return CommandResponse.DECLINED;
        }

        int aliveCnt = 0;
        try {
            aliveCnt = ((SpaceCombatController) getController()).assignHits(player, hitIdxList);
        }
        catch (Exception exception) {
            getController().getUserInterface().reportError("invalid hits assignment: " + exception.getMessage());
            return CommandResponse.DECLINED;
        }

        if (aliveCnt == 0) {
            return CommandResponse.END_EVENT;
        }

        return CommandResponse.ACCEPTED;
    }
}

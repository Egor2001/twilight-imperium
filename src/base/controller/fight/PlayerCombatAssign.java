package base.controller.fight;

import base.controller.AbstractCommand;
import base.controller.CommandResponse;
import base.user.CommandRequestable;
import player.Player;

import java.util.ArrayList;

public class PlayerCombatAssign extends PlayerCombatCommand {

    private ArrayList<Integer> hitIdxList;

    public PlayerCombatAssign(SpaceCombatController controller) {
        super(controller);
        this.controller = controller;
        this.hitIdxList = new ArrayList<>();
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        int hitValue = ((SpaceCombatController) controller).hitValue;
        for (int hitIdx = 0; hitIdx != hitValue; ++hitIdx) {
            hitIdxList.add(userInterface.requestNumber(hitIdx + "'s hit ship idx"));
        }

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        ((SpaceCombatController) getController()).assignHits(player, hitIdxList);
        return CommandResponse.ACCEPTED;
    }
}

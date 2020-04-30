package base.controller.fight;

import base.controller.AbstractCommand;
import base.controller.CommandResponse;
import base.model.GameState;
import base.user.CommandRequestable;
import player.Player;

import java.util.ArrayList;

public class PlayerCombatAssign extends AbstractCommand {

    private ArrayList<Integer> hitIdxList;
    private Integer hitValue;

    public PlayerCombatAssign(SpaceCombatController controller) {
        super(controller);
        this.controller = controller;
        this.hitIdxList = new ArrayList<>();
        this.hitValue = null;
    }

    public void setHitValue(int hitValue) {
        this.hitValue = hitValue;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
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

package base.controller.groundCombat;

import base.controller.CommandResponse;
import base.controller.combat.SpaceCombatController;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import base.view.MessageString;
import player.Player;
import player.units.Unit;

import java.util.ArrayList;

public class PlayerAssignHits extends PlayerGroundCombatCommand {
    private ArrayList<GameObjectTarget> hitUnitsTarget;
    Player player;

    public PlayerAssignHits(GroundCombatController controller, Player player) {
        super(controller);
        this.controller = controller;
        this.hitUnitsTarget = new ArrayList<>();
        this.player = player;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        hitUnitsTarget.clear();

        int hitValue = ((GroundCombatController) controller).getHitValue(player);
        for (int hitIdx = 0; hitIdx != hitValue; ++hitIdx) {
            hitUnitsTarget.add(userInterface.requestTarget("unit for hit"));
        }

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        controller.getUserInterface().displayView(new MessageString("processing GROUND COMBAT command: ASSIGN HITS"));
        Unit unit = null;
        boolean error = false;

        Integer hitValue = ((GroundCombatController) controller).getHitValue(player);
        ArrayList<Unit> units = ((GroundCombatController) controller).getUnits(player);

        if (hitValue > hitUnitsTarget.size()) {
            controller.getUserInterface().displayView(new MessageString("You enter units more then number hits = " + hitValue));
            return CommandResponse.DECLINED;
        }

        for (GameObjectTarget unitTarget: hitUnitsTarget) {
            try {
                unit = (Unit) player.getObject(unitTarget);
            } catch (Exception exception) {
                controller.getUserInterface().displayView(new MessageString("Input invalid target: not found"));
                continue;
            }

            if (unit.getRace() == player.getRace() && ((GroundCombatController) controller).getPlanet() ==
                    controller.getGameState().getTileArmyManager().getTileObject(unit))  {
                --hitValue;
                if (unit.canSustainDamaged() && !unit.isDamaged()) {
                    unit.takeDamaged();
                } else {
                    player.delUnit(unit);
                    controller.getGameState().getTileArmyManager().remove(unit);
                    units.remove(unit);
                }
            } else {
                controller.getUserInterface().displayView(new MessageString("You can hit only self units, but " +
                        unit.getView(null).toString() + " isn't yours"));
                error = true;
            }
        }

        if (error) {
            return CommandResponse.DECLINED;
        }
        if (hitValue > 0) {
            controller.getUserInterface().displayView(new MessageString("Enter " + hitValue + " more units"));
            return CommandResponse.DECLINED;
        }
        return CommandResponse.ACCEPTED;
    }
}

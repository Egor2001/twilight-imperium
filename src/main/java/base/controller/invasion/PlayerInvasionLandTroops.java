package base.controller.invasion;

import base.controller.CommandResponse;
import base.controller.combat.SpaceCombatController;
import base.user.CommandRequestable;
import board.Planet;
import player.Player;
import player.units.GroundForce.GroundForce;
import player.units.GroundForce.Infantry;
import player.units.Unit;

import java.util.ArrayList;

public class PlayerInvasionLandTroops extends PlayerInvasionCommand {
    private ArrayList<GroundForce.Target> targets;
    private Planet.Target planet_target;

    public PlayerInvasionLandTroops(InvasionController controller) {
        super(controller);
        this.targets = new ArrayList<GroundForce.Target>();
        planet_target = null;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        targets.clear();

        int sz = userInterface.requestNumber("Insert number of units to land");

        for (int i = 0; i < sz; ++i) {
            Object unit = userInterface.requestTarget("Insert unit to land");
            if (unit instanceof GroundForce.Target) {
                targets.add((GroundForce.Target)unit);
            }
            else {
                getController().getUserInterface().reportError("Should've got infantry target.\n");

            }
        }

        Object planet = userInterface.requestTarget("Insert planet");
        if (planet instanceof Planet.Target) {
            this.planet_target = (Planet.Target)planet;
        }
        else {
            getController().getUserInterface().reportError("Should've got infantry target.\n");

        }

        return false;
    }

    @Override
    public CommandResponse execute(Player player) {
        InvasionController.CombatPhase phase = ((InvasionController) controller).getCombatPhase();
        if (phase != InvasionController.CombatPhase.LANDING) {
            getController().getUserInterface().reportError("can't land troops in " + phase.toString() + " phase");
            return CommandResponse.DECLINED;
        }

        ArrayList<Unit> units = new ArrayList<>();
        Planet planet = null;

        for (GroundForce.Target target: targets) {
            Object soldier = player.getObject(target);

            if (soldier instanceof Infantry) {
                units.add((Infantry)soldier);
            }
            else {
                getController().getUserInterface().reportError("Should've got infantry to land.\n");
                return CommandResponse.DECLINED;
            }
        }

        planet = (Planet) player.getObject(planet_target);

        ((InvasionController)controller).GetBombardmentManager().Add(units, planet);

        return CommandResponse.ACCEPTED;
    }
}

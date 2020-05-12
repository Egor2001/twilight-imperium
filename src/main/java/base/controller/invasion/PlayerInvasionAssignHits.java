package base.controller.invasion;

import base.controller.CommandResponse;

import base.controller.groundCombat.GroundCombatController;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import base.view.MessageString;
import board.Planet;
import player.Player;
import player.units.GroundForce.GroundForce;
import player.units.Unit;

import java.util.ArrayList;

public class PlayerInvasionAssignHits extends PlayerInvasionCommand  {
    private ArrayList<ArrayList<GameObjectTarget>> hitUnitsTarget;
    private ArrayList<Planet> planets;

    public PlayerInvasionAssignHits(InvasionController controller) {
        super(controller);
        this.controller = controller;
        this.hitUnitsTarget = new ArrayList<>();
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        planets.clear();
        hitUnitsTarget.clear();

        planets = ((InvasionController)controller).GetBombardmentManager().getPlanetsList();
        ArrayList<Integer> hitValue = new ArrayList<Integer>();

        for (Planet planet: planets) {
            hitValue.add(((InvasionController)controller).GetBombardmentManager().getHitOnPlanet(planet));
        }

        boolean go_out = true;

        do {
            go_out = true;

            for (int i = 0; i < planets.size(); ++i) {
                hitUnitsTarget.add(new ArrayList<GameObjectTarget>());
                userInterface.displayView(new MessageString("Choose " + hitValue.get(i) + " units for hit"));

                for (int j = 0; j < hitValue.get(i); ++j) {
                    hitUnitsTarget.get(i).add((GroundForce.Target) userInterface.requestTarget("unit for hit"));
                }
            }

            for (int i = 0; i < planets.size(); ++i) {
                if (hitUnitsTarget.get(i).size() != hitValue.get(i)) {
                    userInterface.displayView(new MessageString("Fuck, you've miscalculated! Try again and be careful."));
                    go_out = false;
                }
            }

        } while (!go_out);

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        controller.getUserInterface().displayView(new MessageString("processing INVASION command: ASSIGN HITS"));
        InvasionController.CombatPhase phase = ((InvasionController) controller).getCombatPhase();
        if (phase != InvasionController.CombatPhase.ASSIGN_HITS) {
            getController().getUserInterface().reportError("can't assign hits in " + phase.toString() + " phase");
            return CommandResponse.DECLINED;
        }

        Unit unit;

        for (int i = 0; i < hitUnitsTarget.size(); ++i) {
            for (GameObjectTarget target : hitUnitsTarget.get(i)) {
                unit = (Unit) player.getObject(target);

                Planet pl = (Planet) controller.getGameState().getTileArmyManager().getTileObject(unit);

                if (pl != planets.get(i)) {
                    return CommandResponse.DECLINED;
                }
            }
        }

        for (ArrayList<GameObjectTarget> list_target: hitUnitsTarget) {
            for (GameObjectTarget target: list_target) {
                unit = (Unit) player.getObject(target);
                if (unit.canSustainDamaged() && !unit.isDamaged()) {
                    unit.takeDamaged();
                } else {
                    player.delUnit(unit);
                    controller.getGameState().getTileArmyManager().remove(unit);
                }
            }

        }

        return CommandResponse.ACCEPTED;
    }
}

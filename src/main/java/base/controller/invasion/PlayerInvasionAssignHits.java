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
    private ArrayList<GameObjectTarget> hitUnitsTarget;

    public PlayerInvasionAssignHits(InvasionController controller) {
        super(controller);
        this.controller = controller;
        this.hitUnitsTarget = new ArrayList<>();
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        ArrayList<ArrayList<GroundForce.Target>> hittingUnits = new ArrayList<ArrayList<GroundForce.Target>>();

        ArrayList<Planet> planets = new ArrayList<Planet>();//(InvasionController)controller.GetPlanetsList();
        ArrayList<Integer> hitValue = new ArrayList<Integer>();

        for (Planet planet: planets) {
            hitValue.add((InvasionController)controller.getHitOnPlanet(planet));
        }

        ArrayList<ArrayList<Unit>> planet_units = new ArrayList<ArrayList<Unit>>();

        for (Planet planet: planets) {
            planet_units.add(controller.getGameState().getTileArmyManager().getUnit(planet));
        }

        boolean go_out = true;

        while (true) {
            go_out = true;

            for (int i = 0; i < planets.size(); ++i) {
                hittingUnits.add(new ArrayList<GroundForce.Target>());
                userInterface.displayView(new MessageString("Choose " + hitValue.get(i) + " units for hit"));

                for (int j = 0; j < hitValue.get(i); ++j) {
                    hittingUnits.get(i).add((GroundForce.Target) userInterface.requestTarget("unit for hit"));
                }
            }

            for (int i = 0; i < planets.size(); ++i) {
                if (hittingUnits.get(i).size() != hitValue.get(i)) {
                    userInterface.displayView(new MessageString("Fuck, you've miscalculated! Try again and be careful."));
                    go_out = false;
                }
            }

            if (go_out) {
                break;
            }
        }

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        controller.getUserInterface().displayView(new MessageString("processing INVASION command: ASSIGN HITS"));
        Unit unit = null;
        boolean error = false;

        while(true) {

        }
        return CommandResponse.ACCEPTED;
    }
}

package base.controller.invasion;

import base.controller.CommandResponse;
import base.controller.combat.SpaceCombatController;
import base.controller.groundCombat.GroundCombatController;
import base.user.CommandRequestable;
import base.view.MessageString;
import board.Planet;
import player.Player;
import player.units.Unit;

import java.util.ArrayList;

public class PlayerInvasionFinish extends PlayerInvasionCommand  {
    public PlayerInvasionFinish(InvasionController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        InvasionController.CombatPhase phase = ((InvasionController) controller).getCombatPhase();
        if (phase != InvasionController.CombatPhase.LANDING) {
            getController().getUserInterface().reportError("can't finish in " + phase.toString() + " phase");
            return CommandResponse.DECLINED;
        }

        ArrayList<Planet> planets = ((InvasionController)controller).GetBombardmentManager().getPlanetsList();
        for (Planet planet: planets) {
            ArrayList<Unit> units = ((InvasionController)controller).GetBombardmentManager().getUnitsOnPlanet(planet);
            if (units.size() > 0) {
                for (Unit unit: units) {
                    controller.getGameState().getTileArmyManager().add(unit, planet);
                }
                
                controller.getUserInterface().displayView(new MessageString("Start battle on\n" +
                        planet.getView(null).toString() + "\n"));

                GroundCombatController groundCombatController = new GroundCombatController(controller.getUserInterface(),
                        controller.getGameState(), controller.getGlobalCommandController(), planet, player);
                groundCombatController.start();
            }
        }

        return CommandResponse.END_EVENT;
    }
}

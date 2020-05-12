package base.controller.invasion;

import base.controller.CommandResponse;
import base.controller.combat.SpaceCombatController;
import base.user.CommandRequestable;
import board.Planet;
import player.Army;
import player.Player;
import player.units.GroundForce.GroundForce;
import player.units.GroundForce.Infantry;
import player.units.Ships.Ship;
import player.units.Unit;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerInvasionBombard extends PlayerInvasionCommand  {
    private ArrayList<Ship.Target> targets;
    private Planet.Target planet_target;


    public PlayerInvasionBombard(InvasionController controller) {
        super(controller);
        this.targets = new ArrayList<Ship.Target>();
        planet_target = null;
    }


    @Override
    public boolean inputCommand(CommandRequestable userInterface) { //What should I return?
        targets.clear();

        int sz = userInterface.requestNumber("Insert number of units");

        for (int i = 0; i < sz; ++i) {
            Object unit = userInterface.requestTarget("Insert unit");
            if (unit instanceof GroundForce.Target) {
                targets.add((Ship.Target)unit);
            }
            else {
                getController().getUserInterface().reportError("Should've got ship target.\n");

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
        ArrayList<Unit> units = new ArrayList<Unit>();
        Planet planet = null;

        for (Ship.Target target: targets) {
            Object soldier = player.getObject(target);

            if (soldier instanceof Ship && ((Ship) soldier).hasBombardment()) {
                units.add((Ship)soldier);
            }
            else {
                getController().getUserInterface().reportError("Should've got infantry.\n");
                return CommandResponse.DECLINED;
            }
        }

        planet = (Planet) player.getObject(planet_target);

        ((InvasionController)controller).GetBombardmentManager().Add(units, planet);

        return CommandResponse.ACCEPTED;
    }
}

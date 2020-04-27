package base.model;

import ArmyUnits.FactoryUnit;
import ArmyUnits.GroundForce.GroundForce;
import ArmyUnits.Ships.Ship;
import ArmyUnits.Structures.PDS;
import ArmyUnits.Structures.SpaceDock;
import ArmyUnits.Unit;
import Races.Race;
import base.Updatable;
import base.controller.HierarchyController.*;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;

//TODO: to implement IUpdatable
public class Player implements Updatable, UserAcceptable {
    private String name;
    private Army army;
    private Race race;

    public Player(String name, String race) throws IllegalArgumentException {
        this.name = name;
        this.army = new Army();
        try {
            this.race = (Race) Class.forName(race).getConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Race invalid: " + race);
        }
    }

    public Unit addUnit(String name) {
        switch (name) {
            case "PDS":
                PDS pds = race.addPDS();
                army.addPDS(pds);
                return pds;
            case "SpaceDock":
                SpaceDock spaceDock = race.addSpaceDock();
                army.addSpaceDock(spaceDock);
                return spaceDock;
            case "Infantry":
                GroundForce groundForce = race.addInfantry();
                army.addGroundForce(groundForce);
                return groundForce;
            default:
                Ship ship = race.addShip(name);
                army.addShip(ship);
                return ship;
        }
    }

    public final String getName() {
        return name;
    }

    public final Army getArmy() {
        return army;
    }

    public static class Target extends GameObjectTarget {
        Target() {
            super();
        }

        Target(Army.Target ArmyTarget) {
            super(ArmyTarget);
        }
    }

    public static class View implements Viewable {
        Viewable armyView;
        String name;

        View(Viewable armyView, String name) {
            this.armyView = armyView;
        }

        @Override
        public void display(Writer writer) throws IOException {
            writer.write("My name is " + name + " and I have army:\n");
            if (armyView != null) {
                armyView.display(writer);
            }
        }
    }

    @Override
    public Viewable getView(UserAcceptable parent) {
        return new View(army.getView(this), name);
    }

    @Override
    public Viewable getView(UserAcceptable parent, GameObjectTarget target) {
        if (target == null) {
            return getView(parent);
        }

        if (target instanceof Army.Target) {
            return army.getView(this, target.getNext());
        }

        return null;
    }

    @Override
    public Object getObject(GameObjectTarget target) {
        if (target == null) {
            return this;
        }

        if (target instanceof Army.Target) {
            return army.getObject(target.getNext());
        }

        return null;
    }

    @Override
    public void update() {
        army.update();
    }
}

package base.model;

import ArmyUnits.FactoryUnit;
import ArmyUnits.GroundForce.GroundForce;
import ArmyUnits.Ships.Ship;
import ArmyUnits.Structures.PDS;
import ArmyUnits.Structures.SpaceDock;
import ArmyUnits.Unit;
import base.Updatable;
import base.controller.HierarchyController.*;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;

//TODO: to implement IUpdatable
public class Player implements Updatable, UserAcceptable {
    private String name;
    private Army army;
    private FactoryUnit raceFactory;

    public Player(String name, String race) {
        this.name = name;
        this.army = new Army();
        this.raceFactory = new FactoryUnit(race);
    }
    public Unit addUnit(String name) {
        switch (name) {
            case "PDS":
                PDS pds = raceFactory.createPDS();
                army.addPDS(pds);
                return pds;
            case "SpaceDock":
                SpaceDock spaceDock = raceFactory.createSpaceDock();
                army.addSpaceDock(spaceDock);
                return spaceDock;
            case "Infantry":
                GroundForce groundForce = raceFactory.createInfantry();
                army.addGroundForce(groundForce);
                return groundForce;
            default:
                try {
                    java.lang.reflect.Method method = raceFactory.getClass().getMethod("create" + name);
                    Ship ship = (Ship) method.invoke(raceFactory);
                    army.addShip(ship);
                    return ship;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                return null;
        }
    }

    public final String getName() {
        return name;
    }

    public final Army getArmy() {
        return army;
    }

    public static class Target extends GameObjectTarget {
        public Target() {
            super();
        }

        public Target(int index) {
            super(index);
        }

        public Target(GameObjectTarget next) {
            super(next);
        }

        public Target(GameObjectTarget next, int index) {
            super(next, index);
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

        @Override
        public String toString(String s) {
            return null;
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

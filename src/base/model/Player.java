package base.model;

import ArmyUnits.FactoryUnit;
import ArmyUnits.Ships.Ship;
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

    public Player(String name, Army army) {
        this.name = name;
        this.army = army;
        this.raceFactory = new FactoryUnit("");
    }
    public Player(String name, String race) {
        this.name = name;
        this.army = new Army();
        this.raceFactory = new FactoryUnit(race);
    }
    public void addUnit(String name) {
        switch (name) {
            case "PDS":
                army.addPDS(raceFactory.createPDS());
                break;
            case "SpaceDock":
                army.addSpaceDock(raceFactory.createSpaceDock());
                break;
            case "Infantry":
                army.addGroundForce(raceFactory.createInfantry());
                break;
            default:
                try {
                    java.lang.reflect.Method method = raceFactory.getClass().getMethod("create" + name);
                    army.addShip((Ship) method.invoke(raceFactory));
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
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

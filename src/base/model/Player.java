package base.model;

import base.Updatable;
import base.controller.HierarchyController.*;

import java.io.IOException;
import java.io.Writer;

//TODO: to implement IUpdatable
public class Player implements Updatable, UserAcceptable {
    private String name;
    private String raceName;
    private Army army;

    public Player(String name, String raceName, Army army) {
        this.name = name;
        this.raceName = raceName;
        this.army = army;
    }
    public Player(String name, String raceName) {
        this.name = name;
        this.raceName = raceName;
        this.army = new Army(raceName);
    }

    public final String getName() {
        return name;
    }
    public final String getRaceName() {
        return raceName;
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

package ArmyUnits;

import Races.Race;
import base.Updatable;
import base.controller.HierarchyController.*;

import java.io.IOException;
import java.io.Writer;

public abstract class Unit implements Updatable, LoaderFromJSON, UserAcceptable {
    private Race race;

    public Unit(Race race) {
        this.race = race;
    }

    class View implements Viewable {
        Unit unit;
        View(Unit unit) {
            this.unit = unit;
        }

        @Override
        public void display(Writer writer) throws IOException {
            String[] className = this.unit.getClass().getName().split("\\.");
            writer.write("My name is " + className[className.length - 2] + "." + className[className.length - 1]);
        }
    }

    @Override
    public Viewable getView(UserAcceptable parent) {
        return new View(this);
    }
    @Override
    public Viewable getView(UserAcceptable parent, GameObjectTarget target) {
        if (target == null) {
            return new View(this);
        }

        return null;
    }

    @Override
    public Object getObject(GameObjectTarget target) {
        if (target == null) {
            return this;
        }

        return null;
    }

    public Race getRace() {
        return race;
    }

    public abstract void printInfo(Writer writer) throws IOException;

    public abstract boolean canFightInSpace();
}

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

    public class View implements Viewable {
        Unit unit;
        View(Unit unit) {
            this.unit = unit;
        }

        @Override
        public void display(Writer writer) throws IOException {
            writer.write(toString());
        }

        public String toString(String start) {
            String[] className = this.unit.getClass().getName().split("\\.");
            String[] classNameRace = this.unit.getRace().getClass().getName().split("\\.");
            return start + className[className.length - 2] + "." + className[className.length - 1] + " (" +
                    classNameRace[classNameRace.length - 1] + ")";
        }
        public String toString() {
            return toString("");
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

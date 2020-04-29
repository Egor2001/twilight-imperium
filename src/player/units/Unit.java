package player.units;

import base.user.GameObjectTarget;
import base.user.UserAcceptable;
import base.view.Viewable;
import player.Race;
import base.model.Updatable;
import player.Army;

import java.io.IOException;
import java.io.Writer;

public abstract class Unit implements Updatable, LoaderFromJSON, UserAcceptable {
    private Race race;
    private Army army;

    public Unit(Race race) {
        this.race = race;
        this.army = null;
    }

    public void setArmy(Army army) {
        this.army = army;
    }
    public Army getArmy() {
        return army;
    }

    public int getIndex() {
        return army.getIndexUnit(this);
    }
    public Race getRace() {
        return race;
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

            String result = start + className[className.length - 1] + " (" + classNameRace[classNameRace.length - 1] + ")";
            if (army != null) {
                result += " #" + getIndex();
            }

            return result;
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

    public void printInfo(Writer writer) throws IOException {
        writer.write(getInfo(""));
    }
    public String getInfo() {
        return getInfo("");
    }
    public abstract String getInfo(String start);

    public abstract boolean canFightInSpace();
}

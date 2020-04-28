package Races;

import ArmyUnits.FactoryUnit;
import ArmyUnits.GroundForce.Infantry;
import ArmyUnits.Ships.Ship;
import ArmyUnits.Structures.PDS;
import ArmyUnits.Structures.SpaceDock;
import base.controller.HierarchyController.*;
import org.json.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class Race implements UserAcceptable {
    private FactoryUnit factoryUnit;
    private HashMap<String, Integer> startingFleet;

    public Race (String name) {
        factoryUnit = new FactoryUnit(name);
        startingFleet = new HashMap<>();

        try (FileReader reader = new FileReader("baseRaces/" + name + ".json")) {
            JSONTokener token = new JSONTokener(reader);
            JSONObject object = new JSONObject(token);

            JSONObject JSONStartingFleet = object.getJSONObject("StartingFleet");
            for (String key: JSONStartingFleet.keySet()) {
                startingFleet.put(key, JSONStartingFleet.getInt(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class View implements Viewable {
        Race race;
        View(Race race) {
            this.race = race;
        }

        @Override
        public void display(Writer writer) throws IOException {
            writer.write(toString());
        }

        public String toString(String start) {
            String[] className = this.race.getClass().getName().split("\\.");
            return start + "Race is " + className[className.length - 2] + "." + className[className.length - 1];
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

    public Ship createShip(String name) {
        try {
            Object[] args = new Object[] {this};
            return (Ship) factoryUnit.getClass().getMethod("create" + name, Race.class).invoke(factoryUnit, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Ship invalid: create" + name);
        }
    }
    public PDS createPDS() {
        return factoryUnit.createPDS(this);
    }
    public SpaceDock createSpaceDock() {
        return factoryUnit.createSpaceDock(this);
    }
    public Infantry createInfantry() {
        return factoryUnit.createInfantry(this);
    }

    public Map<String, Integer> getStartingFleet() {
        return startingFleet;
    }
}

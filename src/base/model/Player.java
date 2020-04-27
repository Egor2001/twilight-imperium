package base.model;

import ArmyUnits.GroundForce.GroundForce;
import ArmyUnits.Ships.Ship;
import ArmyUnits.Structures.PDS;
import ArmyUnits.Structures.SpaceDock;
import ArmyUnits.Unit;
import Races.*;
import base.Updatable;
import base.controller.HierarchyController.*;

import java.io.IOException;
import java.io.Writer;

//TODO: to implement IUpdatable
public class Player implements Updatable, UserAcceptable {
    private String name;
    private Army army;
    private Race race;

    public Player(String name, String race) throws IllegalArgumentException {
        this.name = name;
        this.army = new Army();
        try {
            this.race = (Race) Class.forName("Races." + race).getConstructor().newInstance();
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

    public final Race getRace() {
        return race;
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

    public class View implements Viewable {
        Player player;

        View(Player player) {
            this.player = player;
        }

        @Override
        public void display(Writer writer) throws IOException {
            writer.write(toString());
        }

        public String toString() {
            String result = "My name is " + name + "\n";

            result += player.getRace().getView(player).toString() + "\n";
            result += player.getArmy().getView(player).toString();

            return result;
        }
    }

    @Override
    public Viewable getView(UserAcceptable parent) {
        return new View(this);
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

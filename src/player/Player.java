package player;

import base.user.GameObjectTarget;
import base.user.UserAcceptable;
import base.view.Viewable;
import player.units.GroundForce.GroundForce;
import player.units.Ships.Ship;
import player.units.Structures.PDS;
import player.units.Structures.SpaceDock;
import player.units.Unit;
import base.model.Updatable;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Map;

//TODO: to implement IUpdatable
public class Player implements Updatable, UserAcceptable {
    private String name;
    private Army army;
    private Race race;

    public Player(String name, String race) throws IllegalArgumentException {
        this.name = name;
        this.army = new Army();
        try {
            this.race = (Race) Class.forName("player.races." + race).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Race invalid: " + race);
        }
    }

    public Unit addUnit(String name) {
        switch (name) {
            case "PDS":
                PDS pds = race.createPDS();
                army.addPDS(pds);
                pds.setArmy(army);
                return pds;
            case "SpaceDock":
                SpaceDock spaceDock = race.createSpaceDock();
                army.addSpaceDock(spaceDock);
                spaceDock.setArmy(army);
                return spaceDock;
            case "Infantry":
                GroundForce groundForce = race.createInfantry();
                army.addGroundForce(groundForce);
                groundForce.setArmy(army);
                return groundForce;
            default:
                Ship ship = race.createShip(name);
                army.addShip(ship);
                ship.setArmy(army);
                return ship;
        }
    }
    public ArrayList<Unit> addStartingFleet() {
        Map<String, Integer> startingFleet = race.getStartingFleet();
        ArrayList<Unit> listUnit = new ArrayList<>();

        for (String key: startingFleet.keySet()) {
            for (int i = 0; i < startingFleet.get(key); ++i) {
                listUnit.add(addUnit(key));
            }
        }

        return listUnit;
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

        @Override
        public String toString(String start) {
            String result = start + "My name is " + name + "\n";

            result += player.getRace().getView(player).toString(start + "    ") + "\n";
            result += player.getArmy().getView(player).toString(start + "    ");

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

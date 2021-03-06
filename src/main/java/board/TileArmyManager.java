package board;
import player.units.GroundForce.GroundForce;
import player.units.Ships.Ship;
import player.units.Unit;

import java.util.ArrayList;

public class TileArmyManager {
    public TileArmyManager()  {
        unitList = new ArrayList<Unit>();
        tileObjectsList = new ArrayList<TileObject>();
    }

    private ArrayList<Unit> unitList;
    private ArrayList<TileObject> tileObjectsList;

    public TileObject getTileObject(Unit unit) {
        return tileObjectsList.get(unitList.indexOf(unit));
    }

    public ArrayList<Unit> getUnit(TileObject tileObject) {
        ArrayList<Unit> units = new ArrayList<>();

        for (int i = 0; i < tileObjectsList.size(); ++i) {
            if (tileObject == tileObjectsList.get(i)) {
                units.add(unitList.get(i));
            }
        }

        return units;
    }

    public void add(Unit unit, TileObject tileObject) {
        if (unit instanceof Ship && tileObject instanceof Planet) {
            throw new IllegalArgumentException("Ship must be in Space");
        }
        if (!(unit instanceof Ship) && tileObject instanceof Space) {
            throw new IllegalArgumentException("Non ship must be on Planet");
        }

        unitList.add(unit);
        tileObjectsList.add(tileObject);
    }

    public void move(GroundForce groundForce, TileObject tileObject) {
        remove(groundForce);
        add(groundForce, tileObject);
    }

    public void move(Ship ship, ArrayList<Unit> units, ArrayList<TileObject> way) {
        way.add(0, getTileObject(ship));
        if (way.size() < 2) {
            throw new IllegalArgumentException("In move: way length is less then 2");
        }

        int sizeWay = way.size();

        if (ship.getMoveValue() < sizeWay - 1) {
            throw new IllegalArgumentException("In move: way is too long");
        }

        if (units != null && ship.getCapacityValue() < units.size()) {
            throw new IllegalArgumentException("In move: ship can't carry all this units");
        }

        if (!checkWay(way)) {
            throw new IllegalArgumentException("In move: way is discontinuous");
        }

        for (int i = 1; i < sizeWay - 1; ++i) {
            if (!((Space)way.get(i)).could_fly_throw(ship)) {
                throw new IllegalArgumentException("In move: ship couldn't fly throw all this spaces");
            }
        }

        if (!((Space)way.get(sizeWay - 1)).could_end_flight_in(ship)) {
            throw new IllegalArgumentException("In move: ship can't enter final destination");
        }

        remove(ship);
        add(ship, way.get(sizeWay - 1));
        if (units != null) {
            for (Unit unit: units) {
                remove(unit);
                add(unit, way.get(sizeWay - 1));
            }
        }

        way.remove(0);
    }

    public void move(Ship ship, ArrayList<TileObject> way) {
        move(ship, new ArrayList<>(), way);
    }

    public void remove(Unit unit) {
        int i = unitList.indexOf(unit);
        tileObjectsList.remove(i);
        unitList.remove(i);
    }

    public boolean checkWay(ArrayList<TileObject> way) {
        for (int i = 0; i < way.size() - 1; ++i) {
            if (!way.get(i).My_neighbours().contains(way.get(i + 1))) {
                return false;
            }
        }
        return true;
    }
}

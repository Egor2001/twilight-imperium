package tile;
import ArmyUnits.Ships.Ship;
import ArmyUnits.Unit;

import java.util.ArrayList;

public class TileArmyController {
    public TileArmyController()  {
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
        unitList.add(unit);
        tileObjectsList.add(tileObject);
    }

    public void move(Ship ship, ArrayList<Unit> units, ArrayList<TileObject> way) {
        way.add(0, getTileObject(ship));
        if (way.size() < 2) {
            throw new IllegalArgumentException("In move: way length is less then 2\n");
        }

        int sizeWay = way.size();

        if (ship.getMoveValue() < sizeWay - 1) {
            throw new IllegalArgumentException("In move: way is too long\n");
        }

        if (ship.getCapacityValue() < units.size()) {
            throw new IllegalArgumentException("In move: ship can't carry all this units\n");
        }

        if (!checkWay(way)) {
            throw new IllegalArgumentException("In move: way is discontinuous\n");
        }

        for (int i = 1; i < sizeWay - 1; ++i) {
            if (!((Space)way.get(i)).could_fly_throw(ship)) {
                throw new IllegalArgumentException("In move: ship couldn't fly throw all this spaces\n");
            }
        }

        if (!((Space)way.get(sizeWay - 1)).could_end_flight_in(ship)) {
            throw new IllegalArgumentException("In move: ship can't enter final destination\n");
        }

        remove(ship);
        add(ship, way.get(sizeWay - 1));
        for (Unit unit: units) {
            remove(unit);
            add(unit, way.get(sizeWay));
        }
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

package tile;
import ArmyUnits.Ships.Ship;
import ArmyUnits.Unit;

import java.util.ArrayList;

public class TileArmyController {
    public TileArmyController()
    {
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

    public boolean move(Ship ship, ArrayList<Unit> units, ArrayList<TileObject> way) {
        way.add(0, getTileObject(ship));

        int sizeWay = way.size();
        if (ship.getMoveValue() < sizeWay - 1 || ship.getCapacityValue() < units.size()) {
            return false;
        }

        if (!checkWay(way)) {
            return false;
        }

        for (int i = 1; i < sizeWay - 1; ++i) {
            if (!((Space)way.get(i)).could_fly_throw(ship)) {
                return false;
            }
        }

        if (!((Space)way.get(sizeWay - 1)).could_end_flight_in(ship)) {
            return false;
        }

        remove(ship);
        add(ship, way.get(sizeWay - 1));
        for (Unit unit: units) {
            remove(unit);
            add(unit, way.get(sizeWay));
        }

        return true;
    }

    public boolean move(Ship ship, ArrayList<TileObject> way) {
        return move(ship, new ArrayList<>(), way);
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

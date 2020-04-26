package tile;
import ArmyUnits.Ships.Ship;
import ArmyUnits.Unit;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

class Unit{

}

public class TileArmyController {
    private ArrayList<Unit> unitList;
    private ArrayList<TileObject> tileObjectsList;

    TileObject getTileObject(Unit unit) {
        return tileObjectsList.get(unitList.indexOf(unit));

    }

    ArrayList<Unit> getUnit(TileObject tileObject) {
        ArrayList<Unit> units = new ArrayList<>();

        for (int i = 0; i < tileObjectsList.size(); ++i) {
            if (tileObject == tileObjectsList.get(i)) {
                units.add(unitList.get(i));
            }
        }

        return units;
    }

    void add(Unit unit, TileObject tileObject) {
        unitList.add(unit);
        tileObjectsList.add(tileObject);
    }

    boolean move(Ship ship, ArrayList<Unit> units, ArrayList<TileObject> way) {
        int sizeWay = way.size();
        if (ship.getMoveValue() < sizeWay || ship.getCapacityValue() < units.size()) {
            return false;
        }

        if (!checkWay(way)) {
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

    boolean move(Ship ship, ArrayList<TileObject> way) {
        return move(ship, new ArrayList<>(), way);
    }

    void remove(Unit unit) {
        int i = unitList.indexOf(unit);
        tileObjectsList.remove(i);
        unitList.remove(i);
    }

    boolean checkWay(ArrayList<TileObject> way) {
        for (int i = 0; i < way.size() - 1; ++i) {
            if (!way.get(i).My_neighbours().contains(way.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    ArrayList<ArrayList<Object>> ListTileObjectUnit;

    TileObject GetTileObject(Unit unit) {
        for (ArrayList<Object> i: ListTileObjectUnit)  {
            if ((Unit)i.get(1) == unit) {
                return (TileObject)(i.get(0));
            }
        }

        return null;
    }

    ArrayList<Unit> GetUnitsList(TileObject tile_object) {
        ArrayList<Unit> answer = new ArrayList<Unit>();

        for (ArrayList<Object> i: ListTileObjectUnit)  {
            if ((TileObject)i.get(0) == tile_object) {
                answer.add((Unit)(i.get(1)));
            }
        }

        return answer;
    }

    void Connect(TileObject tileObject, Unit unit) {
        ArrayList<Object> answer = new ArrayList<Object>();
        answer.add(tileObject);
        answer.add(unit);

        ListTileObjectUnit.add(answer);
    }

    void Disconnect(TileObject tileObject, Unit unit) {
        for (ArrayList<Object> i: ListTileObjectUnit)  {
            if ((TileObject)i.get(0) == tileObject && (Unit)i.get(1) == unit) {
                ListTileObjectUnit.remove(i);
            }
        }
    }
}

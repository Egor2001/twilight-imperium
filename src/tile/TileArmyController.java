package tile;
import base.model.Army;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

class Unit{

}

public class TileArmyController {
    private <K, V> K getKey(Map<K, V> map, V value) {

        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }

        return null;
    }

    private Map<TileObject, Army> dictionary; //= new HashMap<TileObject,Army>();

    Army GetArmy(TileObject tile_object) {
        return dictionary.get(tile_object);
    }

    TileObject GetTileObject(Army army) {
        return getKey(dictionary, army);
    }



    void Connect(TileObject tileObject, Army army) {
        dictionary.put(tileObject, army);
    }

    void Disconnect(TileObject tileObject, Army army) {
        dictionary.remove(tileObject);
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

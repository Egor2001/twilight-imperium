package tile;
import base.Army;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
}

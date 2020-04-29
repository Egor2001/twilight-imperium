package base.user;

import player.units.GroundForce.GroundForce;
import player.units.Ships.Ship;
import player.units.Structures.PDS;
import player.units.Structures.SpaceDock;
import player.Army;
import player.Player;
import board.*;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class HierarchyManager {

    public static final HashMap<String, Class<? extends GameObjectTarget>> targetMap;

    static {
        targetMap = new HashMap<>();

        targetMap.put("Board", Board.Target.class);
        targetMap.put("Tile", Tile.Target.class);
        targetMap.put("TileObject", TileObject.Target.class);
        targetMap.put("Space", Space.Target.class);
        targetMap.put("Planet", Planet.Target.class);

        targetMap.put("Player", Player.Target.class);
        targetMap.put("Army", Army.Target.class);
        targetMap.put("Ship", Ship.Target.class);
        targetMap.put("GroundForce", GroundForce.Target.class);
        targetMap.put("PDS", PDS.Target.class);
        targetMap.put("SpaceDock", SpaceDock.Target.class);
    }

    public static GameObjectTarget parseTarget(String inputStr) throws IllegalArgumentException {
        GameObjectTarget target = null;
        String[] strArr = inputStr.split("[\\[\\].,;]+");

        Integer index = null;
        for (int i = strArr.length - 1; i >= 0; --i) {
            if (strArr[i].matches("\\d+")) {
                if (index != null) {
                    throw new IllegalArgumentException("2 subsequent indices in path");
                }

                index = Integer.decode(strArr[i]);
            }
            else {
                Class<? extends GameObjectTarget> newTargetClass = targetMap.get(strArr[i]);
                if (newTargetClass == null) {
                    throw new IllegalArgumentException("no game object named " + strArr[i]);
                }

                Constructor<? extends GameObjectTarget> constructor = null;
                try {
                    constructor = (index == null ?
                            newTargetClass.getConstructor(GameObjectTarget.class) :
                            newTargetClass.getConstructor(GameObjectTarget.class, int.class));
                } catch (Exception error) {
                    throw new IllegalArgumentException("no specified constructor for " + strArr[i]);
                }

                try {
                    target = (GameObjectTarget) (index == null ?
                            constructor.newInstance(target) :
                            constructor.newInstance(target, index));
                } catch (Exception error) {
                    throw new IllegalArgumentException("can' t create specified target for " + strArr[i]);
                }

                index = null;
            }
        }

        return target;
    }
}

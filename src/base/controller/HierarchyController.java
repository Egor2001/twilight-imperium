package base.controller;

import ArmyUnits.Ships.Ship;
import base.model.Player;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class HierarchyController {

    public static final HashMap<String, Class<? extends GameObjectTarget>> targetMap;

    static {
        targetMap = new HashMap<>();

        targetMap.put("parent", base.Main.Parent.Target.class);
        targetMap.put("child", base.Main.Child.Target.class);
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

                Constructor<?>[] constructorsArr = newTargetClass.getConstructors();
                for (Constructor<?> constructor : constructorsArr) {
                    try {
                        if (index != null) {
                            target = (GameObjectTarget) constructor.newInstance(target, index);
                        }
                        else {
                            target = (GameObjectTarget) constructor.newInstance(target);
                        }
                    } catch (Exception error) {
                        continue;
                    }

                    break;
                }

                if (target == null) {
                    throw new IllegalArgumentException("can' t create specified target for " + strArr[i]);
                }

                index = null;
            }
        }

        return target;
    }

    public HierarchyController() {}

    public interface Viewable {
        void display(Writer writer) throws IOException;
    }

    public interface UserAcceptable {
        Viewable getView(UserAcceptable parent);
        Viewable getView(UserAcceptable parent, GameObjectTarget target);
        Object getObject(GameObjectTarget target);
    }

    public static abstract class GameObjectTarget {

        private GameObjectTarget next;
        int index;

        public GameObjectTarget() {
            this.next = null;
            this.index = 0;
        }
        public GameObjectTarget(GameObjectTarget next) {
            this.next = next;
            this.index = 0;
        }

        public GameObjectTarget(int index) {
            this.next = null;
            this.index = index;
        }
        public GameObjectTarget(GameObjectTarget next, int index) {
            this.next = next;
            this.index = index;
        }

        public GameObjectTarget getNext() {
            return next;
        }

        public int getIndex() {
            return index;
        }
    }
}

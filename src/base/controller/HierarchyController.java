package base.controller;

import java.io.OutputStream;
import java.util.HashMap;

public class HierarchyController {

    HashMap<String, GameObjectTarget> targetMap;

    public HierarchyController() {
    }

    public interface Viewable {
        void display(OutputStream stream);
    }

    public interface UserAcceptable {
        Viewable getView(UserAcceptable parent, GameObjectTarget target);
        Object getObject(GameObjectTarget target);
    }

    public static abstract class GameObjectTarget {

        private GameObjectTarget next;

        public GameObjectTarget() {
            this.next = null;
        }

        public GameObjectTarget(GameObjectTarget next) {
            this.next = next;
        }

        public GameObjectTarget getNext() {
            return next;
        }
    }
}

package base.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;

public class HierarchyController {

    HashMap<String, GameObjectTarget> targetMap;

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

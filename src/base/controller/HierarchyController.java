package base.controller;

import java.io.OutputStream;

public class HierarchyController {

    public HierarchyController() {
    }

    public interface Viewable {
        void display(OutputStream stream);
    }

    public interface UserAcceptable {
        Viewable getView(UserAcceptable parent, PlayerCommand command);
        Object getObject(PlayerCommand command);
    }

    public static abstract class PlayerCommand {

        private PlayerCommand parent;

        public PlayerCommand() {
            this.parent = null;
        }

        public PlayerCommand(PlayerCommand parent) {
            this.parent = parent;
        }

        public PlayerCommand getParent() {
            return parent;
        }
    }
}

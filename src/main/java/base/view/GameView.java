package base.view;

import base.model.GameState;

import java.io.PrintStream;

public class GameView {

    private GameState gameState;
    private PrintStream printStream;

    public interface Composite {
        Composite getParent();
    }

    public static abstract class CompositeCommand {

        private CompositeCommand parent;

        public CompositeCommand getParent() {
            return parent;
        }

        public abstract Boolean isMatch(String commandStr);
        public abstract Boolean canProc(Composite composite);
    }
/*
    public interface Viewable extends Composite {
        void view(Viewable parent);
        void view(Viewable parent, CompositeCommand command);
    }
*/
    public GameView(GameState gameState, PrintStream printStream) {
        this.gameState = gameState;
        this.printStream = printStream;
    }

    public void viewBoard() {

    }

    public void viewPlayer() {

    }
}

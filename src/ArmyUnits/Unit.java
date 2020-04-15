package ArmyUnits;

import base.Updatable;
import base.controller.HierarchyController.*;

import java.io.IOException;
import java.io.Writer;

public interface Unit extends Updatable, LoaderFromJSON, UserAcceptable {
    int getCost();
    boolean canHit(int diceValue);

    void setCombatValue(int newCombatValue);
    void setCost(int newCost);

    class Target extends GameObjectTarget {
        Target() {
            super();
        }
        Target(int index) {
            super(index);
        }
    }

    class View implements Viewable {
        View() {}

        @Override
        public void display(Writer writer) throws IOException {
            String[] className = this.getClass().getName().split("\\.");
            writer.write("My name is " + className[className.length - 1] + "\n");
        }
    }

    @Override
    default Viewable getView(UserAcceptable parent) {
        return new View();
    }

    @Override
    default Viewable getView(UserAcceptable parent, GameObjectTarget target) {
        if (target == null) {
            return new View();
        }

        return null;
    }

    @Override
    default Object getObject(GameObjectTarget target) {
        if (target == null) {
            return this;
        }

        return null;
    }
}

package ArmyUnits;

import base.Updatable;
import base.controller.HierarchyController.*;

import java.io.IOException;
import java.io.Writer;

public interface Unit extends Updatable, LoaderFromJSON, UserAcceptable {
    class View implements Viewable {
        Unit unit;
        View(Unit unit) {
            this.unit = unit;
        }

        @Override
        public void display(Writer writer) throws IOException {
            String[] className = this.unit.getClass().getName().split("\\.");
            writer.write("My name is " + className[className.length - 2] + "." + className[className.length - 1]);
        }
    }

    @Override
    default Viewable getView(UserAcceptable parent) {
        return new View(this);
    }
    @Override
    default Viewable getView(UserAcceptable parent, GameObjectTarget target) {
        if (target == null) {
            return new View(this);
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

    void printInfo(Writer writer) throws IOException;

    boolean canFightInSpace();
}

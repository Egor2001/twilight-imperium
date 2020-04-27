package tile;

import ArmyUnits.Ships.Ship;
import ArmyUnits.Unit;
import base.controller.HierarchyController;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Space extends TileObject{
    public Space(Tile my_tile) {
        super(my_tile);
    };

    public boolean could_fly_throw(Ship ship) {
        return true;
    }
    public boolean could_end_flight_in(Ship ship)  {
        return true;
    }

    @Override
    public HierarchyController.Viewable getView(HierarchyController.UserAcceptable parent) {
        return this.new View();
    }
    @Override
    public HierarchyController.Viewable getView(HierarchyController.UserAcceptable parent, HierarchyController.GameObjectTarget target) {
        return null;
    }
    @Override
    public Object getObject(HierarchyController.GameObjectTarget target) throws Exception {
        if (target == null)
        {
            return this;
        }
        else
        {
            throw new Exception("Space has no child");
        }
    }

    public class View implements HierarchyController.Viewable{
        View(){};

        @Override
        public String toString() {
            return new String(toString(""));
        }
        public String toString(String string) {
            StringBuilder answer = new StringBuilder(string + "space{\n");

            //ArrayList<Unit> units = GetObjectUnits();

            //for (Unit unit: units) {
                //answer.append(unit.getView(null).toString(string + "    "));
            //}
            //
            answer.append(string + "}");
            return answer.toString();
        }
        public String to_String(String start) {
            return new String(start + toString());
        }

        @Override
        public void display(Writer writer) throws IOException {

        }
    };

    public static class Target extends HierarchyController.GameObjectTarget{
        public Target() {
            super();
        }

        public Target(HierarchyController.GameObjectTarget next) {
            super(next);
        }
    }
};

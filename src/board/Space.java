package board;

import base.user.GameObjectTarget;
import base.user.UserAcceptable;
import base.view.Viewable;
import player.units.Ships.Ship;
import player.units.Unit;

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
    public Viewable getView(UserAcceptable parent) {
        return this.new View();
    }
    @Override
    public Viewable getView(UserAcceptable parent, GameObjectTarget target) {
        return null;
    }
    @Override
    public Object getObject(GameObjectTarget target) throws Exception {
        if (target == null)
        {
            return this;
        }
        else
        {
            throw new Exception("Space has no child");
        }
    }

    public class View implements Viewable {
        View(){};

        @Override
        public String toString() {
            return new String(toString(""));
        }
        public String toString(String string) {
            StringBuilder answer = new StringBuilder(string + "space{\n");

            ArrayList<Unit> units = GetObjectUnits();

            for (Unit unit: units) {
                answer.append(unit.getView(null).toString(string + "    ")).append("\n");
            }

            answer.append(string).append("}");
            return answer.toString();
        }
        public String to_String(String start) {
            return new String(start + toString());
        }

        @Override
        public void display(Writer writer) throws IOException {

        }
    }

    public static class Target extends GameObjectTarget {
        public Target() {
            super();
        }

        public Target(GameObjectTarget next) {
            super(next);
        }
    }
}
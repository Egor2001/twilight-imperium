package board;

import base.user.GameObjectTarget;
import base.user.UserAcceptable;
import base.view.Viewable;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class Planet extends TileObject {
    public Planet(String planet_name, Tile my_tile) {
        super(my_tile);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(
                "/etc/basePlanets/" + planet_name + ".json")))) {
            JSONTokener token = new JSONTokener(reader);
            JSONObject object = new JSONObject(token);

            Name = (String)object.get("name");
            Type = (String)object.get("type");
            Resource = (int)object.get("resource");
            Influence = (int)object.get("influence");
            Tech = (String)object.get("tech");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    String Name;
    String Type;
    int Resource;
    int Influence;
    String Tech;

    public void print() {
        print("");
    }
    public void print(String s) {
        System.out.println(s + "planet{" + Name + " " + Type + " " + Resource + " " + Influence + " " + Tech + "}");
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
            throw new Exception("Planet has no child");
        }
    }

    public class View implements Viewable {
        View(){};
        @Override
        public String toString() {
            return new String("planet{" + Name + " " + Type + " " + Resource + " " + Influence + " " + Tech + "}");
        }

        @Override
        public String toString(String s) {
            return to_String(s);
        }

        //@Override
        public String to_String(String start) {
            return new String(start + toString());
        }

        @Override
        public void display(Writer writer) throws IOException {

        }
    };

    public static class Target extends GameObjectTarget {
        private int index_;

        public Target() {
            super();
        }

        public Target(GameObjectTarget next) {
            super(next);
        }

        public Target(int index) {
            super();
            index_ = index;
        }

        public Target(GameObjectTarget next, int index) {
            super(next);
            index_ = index;
        }

        public int getIndex() {
            return index_;
        }
    }

    /*class View implements HierarchyController.Viewable {
        @Override
        public void display(Writer writer) throws IOException {
            //print();
        }
    }*/
}

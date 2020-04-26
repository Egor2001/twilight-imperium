package tile;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

import base.controller.HierarchyController;

class Planet extends TileObject{
    public Planet(String planet_name) {
        try (FileReader reader = new FileReader("basePlanets/" + planet_name + ".json")) {
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
    public HierarchyController.Viewable getView(HierarchyController.UserAcceptable parent) {
        return null;
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
            throw new Exception("Planet has no child");
        }
    }

    public static class Target extends HierarchyController.GameObjectTarget{
        private int index_;

        public Target() {
            super();
        }

        public Target(HierarchyController.GameObjectTarget next) {
            super(next);
        }

        public Target(int index) {
            super();
            index_ = index;
        }

        public Target(HierarchyController.GameObjectTarget next, int index) {
            super(next);
            index_ = index;
        }

        public int getIndex() {
            return index_;
        }
    }

    class View implements HierarchyController.Viewable {

        @Override
        public void display(Writer writer) throws IOException {
            //print();
        }
    }
}

package tile;

import base.controller.HierarchyController;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.print.DocFlavor;
import java.io.FileReader;
import java.util.ArrayList;

public class Tile implements HierarchyController.UserAcceptable {
    public Tile(int num, int game_num, Board board)  {
        planets_ = new ArrayList<>();
        space_ = new Space();
        LoadTile(num);
        num_ = game_num;
        board_ = board;
    }

    public Tile(ArrayList<String> planet_names) {
        planets_ = new ArrayList<>();
        space_ = new Space();

        for (String name: planet_names) {
            planets_.add(new Planet(name));
        }
    }

    ArrayList<Tile> Tile_neighbours() {
        return board_.MyNeighbours(num_);
    }

    ArrayList<TileObject> Object_neighbours(TileObject object) {
        if (object instanceof Space) {
            ArrayList<TileObject> answer = new ArrayList<>();

            answer.addAll(0, planets_);

            ArrayList<Tile> neighbours_ = Tile_neighbours();

            for (int i = 0; i < neighbours_.size(); ++i)  {
                answer.add((TileObject)((neighbours_.get(i)).space_));
            }

            return answer;
        }
        else if (object instanceof Planet) {
            ArrayList<TileObject> answer = new ArrayList<>();
            answer.add((TileObject)space_);
            return answer;
        }

        return null;
    }

    public Space GetSpace(){ return space_;}
    public ArrayList<Planet> GetPlanets(){ return planets_;}

    private ArrayList<Planet> planets_;
    private Space space_;
    private int num_;
    private Board board_;

    public void LoadTile(int num)  {
        try (FileReader reader = new FileReader("baseTiles/tile" + (num) + ".json")) {
            JSONTokener token = new JSONTokener(reader);
            JSONObject object = new JSONObject(token);

            int sz = (int)object.get("planets_num");
            ArrayList<Integer> list = new ArrayList<Integer>();

            for (int i = 0; i < sz; i++) {
                planets_.add(new Planet((String) object.get("planet" + i)));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            if (target instanceof Planet.Target)
            {
                return planets_.get(((Planet.Target) target).getIndex()).getObject(target.getNext());
            } else if (target instanceof Space.Target)
            {
                return space_;
            }
        }

        return null;
    }

    public static class Target extends HierarchyController.GameObjectTarget {
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

    public void print()
    {
        print("");
    }

    public void print(String spaces) {
        System.out.println(spaces + "tile {");

        for (Planet planet: planets_)
        {
            planet.print(spaces + "    ");
        }

        System.out.println("}");
    }
}

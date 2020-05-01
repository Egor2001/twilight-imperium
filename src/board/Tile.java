package board;

import base.user.GameObjectTarget;
import base.user.UserAcceptable;
import base.view.Viewable;
import player.units.Unit;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Tile implements UserAcceptable {
    public Tile(int num, int game_num, Board board)  {
        planets_ = new ArrayList<>();
        space_ = new Space(this);
        LoadTile(num);
        num_ = game_num;
        board_ = board;
    }
    public Tile(ArrayList<String> planet_names) {
        planets_ = new ArrayList<>();
        space_ = new Space(this);

        for (String name: planet_names) {
            planets_.add(new Planet(name, this));
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
    public Space space_;
    private int num_;
    private Board board_;

    public void LoadTile(int num)  {
        try (FileReader reader = new FileReader("etc/baseTiles/tile" + (num) + ".json")) {
            JSONTokener token = new JSONTokener(reader);
            JSONObject object = new JSONObject(token);

            int sz = (int)object.get("planets_num");

            for (int i = 0; i < sz; i++) {
                planets_.add(new Planet((String) object.get("planet" + i), this));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public ArrayList<Unit> GetObjectUnits(TileObject object) {
        return board_.GetObjectUnits(object);
    }
    public int getId(){
        return num_;
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

    public class View implements Viewable {
        @Override
        public String toString() {
            return toString("");
        }

        public String toString(String start) {
            StringBuilder answer = new StringBuilder(new String(start + "Tile: " + num_ + " {\n"));

            for (Planet plane: planets_)
                answer.append(plane.new View().to_String(start + "    ")).append("\n");

            answer.append(space_.new View().toString(start + "    ")).append("\n");
            answer.append(start).append("}");

            return answer.toString();
        }

        @Override
        public void display(Writer writer) throws IOException {

        }
    };

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

package board;

import base.user.GameObjectTarget;
import base.user.UserAcceptable;
import base.view.Viewable;
import player.units.Unit;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;

public class Board implements UserAcceptable {
    public Board(TileArmyManager controller) {
        ta_controller = controller;
        ArrayList<Integer> tile_list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(
                "/etc/BoardStructure/6players.json")))) {
            JSONTokener token = new JSONTokener(reader);
            JSONObject object = new JSONObject(token);

            size_ = (int)object.get("size");

            for (int i = 0; i < size_; i++) {
                tile_list.add((int)object.get("tile" + i));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        tiles_ = new ArrayList<Tile>();
        bonds_ = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < tile_list.size(); ++i) {
            tiles_.add(new Tile(tile_list.get(i), i, this));
        }

        for (int k = 0; k < size_; ++k) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(
                    "/etc/Board/" + (k) + ".json")))) {
                JSONTokener token = new JSONTokener(reader);
                JSONObject object = new JSONObject(token);

                int sz = (int)object.get("neighbours_num");
                ArrayList<Integer> list = new ArrayList<Integer>();

                for (int i = 0; i < sz; i++) {
                    int x = (int)object.get("neighbour" + i);
                    if (x < size_)
                        list.add(x);
                }
                //list = (ArrayList<Integer>)object.get("neighbour");

                bonds_.add(list);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public Board() {
        tiles_ = new ArrayList<Tile>();
        bonds_ = new ArrayList<ArrayList<Integer>>();
    }
  
    public ArrayList<Tile> tiles_;
    public ArrayList<ArrayList<Integer>> bonds_;
    public int size_;
    public TileArmyManager ta_controller;

    public TileArmyManager getTileArmyManager() {
        return ta_controller;
    }

    void print() {

    }
    public ArrayList<Tile> MyNeighbours(int tile_index) {
        ArrayList<Tile> answer = new ArrayList<Tile>();
        for (int i: bonds_.get(tile_index)) {
            answer.add(tiles_.get(i));
        }

        return answer;
    }

    public ArrayList<Unit> GetObjectUnits(TileObject object) {
        return ta_controller.getUnit(object);
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
            if (target instanceof Tile.Target)
            {
                return tiles_.get(((Tile.Target) target).getIndex()).getObject(target.getNext());
            } else
            {
                throw new Exception("Wrong target request");
            }
        }
    }

    public class View implements Viewable {
        @Override
        public String toString() {
            return toString("");
            //return new String("planet{" + Name + " " + Type + " " + Resource + " " + Influence + " " + Tech + "}");
        }

        public String toString(String start) {
            StringBuilder answer = new StringBuilder(new String(start + "Board {\n"));

            for (Tile tile: tiles_)
                answer.append(tile.new View().toString(start + "    ")).append("\n");

            answer.append(start).append("}");

            return answer.toString();
        }

        @Override
        public void display(Writer writer) throws IOException {

        }
    };
    public static class Target extends GameObjectTarget {
        public Target() {
            super();
        }

        public Target(GameObjectTarget next_) {
            super(next_);
        }
    }
}

package tile;

import base.controller.HierarchyController;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.util.ArrayList;

public class Board implements HierarchyController.UserAcceptable {
    public Board(boolean r) {
        ArrayList<Integer> tile_list = new ArrayList<Integer>();

        try (FileReader reader = new FileReader("BoardStructure/6players.json")) {
            JSONTokener token = new JSONTokener(reader);
            JSONObject object = new JSONObject(token);

            size_ = (int)object.get("size");

            for (int i = 0; i < size_; i++) {
                tile_list.add((int)object.get("tile" + i));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        tiles_ = new ArrayList<Tile>();
        bonds_ = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < tile_list.size(); ++i) {
            tiles_.add(new Tile(tile_list.get(i), i, this));
        }

        for (int k = 0; k < size_; ++k) {
            try (FileReader reader = new FileReader("Board/" + (k) + ".json")) {
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

    void print() {

    }
    public ArrayList<Tile> MyNeighbours(int tile_index) {
        ArrayList<Tile> answer = new ArrayList<Tile>();
        for (int i: bonds_.get(tile_index)) {
            answer.add(tiles_.get(i));
        }

        return answer;
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
            if (target instanceof Tile.Target)
            {
                return tiles_.get(((Tile.Target) target).getIndex()).getObject(target.getNext());
            } else
            {
                throw new Exception("Wrong target request");
            }
        }
    }

    public static class Target extends HierarchyController.GameObjectTarget{
        public Target() {
            super();
        }

        public Target(HierarchyController.GameObjectTarget next_) {
            super(next_);
        }
    }
}

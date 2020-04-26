package tile;

import base.controller.HierarchyController;

import java.util.ArrayList;

public class Board implements HierarchyController.UserAcceptable {
    public Board(ArrayList<Integer> board_tiles)
    {
        tiles_ = new ArrayList<Tile>();
        bonds_ = new ArrayList<ArrayList<Integer>>();

        for (int index: board_tiles) {
            tiles_.add(new Tile(index));
            bonds_.add(tiles_.get(tiles_.size() - 1).LoadTile(index));
        }
    }

    public Board()
    {
        tiles_ = new ArrayList<Tile>();
        bonds_ = new ArrayList<ArrayList<Integer>>();
    }

    public void AddTile(Tile tile)
    {
        tiles_.add(tile);
        bonds_.add(new ArrayList<Integer>());
    }

    public void AddBond(int i, int j)
    {
        bonds_.get(i).add(j);
        bonds_.get(j).add(i);
    }

    public ArrayList<Tile> tiles_;
    public ArrayList<ArrayList<Integer>> bonds_;

    void print() {

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

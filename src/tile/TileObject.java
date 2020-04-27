package tile;
import base.controller.HierarchyController;
import base.model.Army;
import base.model.Player;

import java.util.ArrayList;

public class TileObject implements HierarchyController.UserAcceptable{
    TileObject () {
    }

    public void Invade() {}

    public void Allowed_to_invade() {}

    public ArrayList<TileObject> My_neighbours() {
        return my_tile.Object_neighbours(this);
    }

    public Tile GetTile() { return my_tile; }

    Player owner_;
    private Tile my_tile;
    private ArrayList<TileObject> neighbours_;

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

        public Target() {
            super();
        }

        public Target(int index) {
            super(index);
        }

        public Target(HierarchyController.GameObjectTarget next) {
            super(next);
        }

        public Target(HierarchyController.GameObjectTarget next, int index) {
            super(next, index);
        }
    }
}
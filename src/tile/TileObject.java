package tile;
import ArmyUnits.Unit;
import base.controller.HierarchyController;
import base.model.Army;
import base.model.Player;

import java.util.ArrayList;

public class TileObject implements HierarchyController.UserAcceptable{
    TileObject (Tile my_tile_) {
        my_tile = my_tile_;
    }

    public ArrayList<TileObject> My_neighbours() {
        return my_tile.Object_neighbours(this);
    }
    public Tile GetTile() { return my_tile; }
    public ArrayList<Unit> GetObjectUnits() {
        return my_tile.GetObjectUnits(this);
    }

    //Player owner_;
    private Tile my_tile;

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
        return null;
    }

    public static class Target extends HierarchyController.GameObjectTarget{
    }
}
package board;
import base.user.GameObjectTarget;
import base.user.UserAcceptable;
import player.units.Unit;

import java.util.ArrayList;

public abstract class TileObject implements UserAcceptable {
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

    public static class Target extends GameObjectTarget {
    }
}
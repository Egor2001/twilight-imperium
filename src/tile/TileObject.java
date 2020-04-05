package tile;
import base.model.Army;
import base.model.Player;

import java.util.ArrayList;

public class TileObject {
    public void Invade() {}

    public void Allowed_to_invade() {}

    public ArrayList<TileObject> My_neighbours() {
        return my_tile.Object_neighbours(this);
    }

    Player owner_;
    private Tile my_tile;
    private ArrayList<TileObject> neighbours_;
}
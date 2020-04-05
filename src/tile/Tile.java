package tile;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;

public class Tile {
    ArrayList<Tile> Tile_neighbours() {
        return neighbours_;
    }

    ArrayList<TileObject> Object_neighbours(TileObject object) {
        if (object instanceof Space) {
            ArrayList<TileObject> answer = new ArrayList<TileObject>();

            answer.addAll(0, planets_);

            for (int i = 0; i < neighbours_.size(); ++i)  {
                answer.add((TileObject)((neighbours_.get(i)).space_));
            }

            return answer;
        }
        else if (object instanceof Planet) {
            ArrayList<TileObject> answer = new ArrayList<TileObject>();
            answer.add((TileObject)space_);
            return answer;
        }

        return null;
    }

    private ArrayList<TileObject> planets_;
    private Space space_;
    private ArrayList<Tile> neighbours_;
}

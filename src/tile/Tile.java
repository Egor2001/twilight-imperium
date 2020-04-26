package tile;

import base.controller.HierarchyController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;

public class Tile implements HierarchyController.UserAcceptable {
    public Tile()
    {
        planets_ = new ArrayList<Planet>();
        space_ = new Space();
    }
    public Tile(ArrayList<String> planet_names)
    {
        planets_ = new ArrayList<Planet>();
        space_ = new Space();

        for (String name: planet_names) {
            planets_.add(new Planet(name));
        }
    }


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

    private ArrayList<Planet> planets_;
    private Space space_;
    private ArrayList<Tile> neighbours_;

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
            } else
            {
                if (target instanceof Space.Target)
                {
                    return planets_.get(((Space.Target) target).getIndex()).getObject(target.getNext());
                } else{
                    throw new Exception("Wrong target request");
                }
            }
        }
    }

    static class Target extends HierarchyController.GameObjectTarget {
        private HierarchyController.GameObjectTarget next;
        private int index_;

        public Target() {
            this.next = null;
        }

        public Target(HierarchyController.GameObjectTarget next, int index) {
            this.next = next;
            index_ = index;
        }

        public HierarchyController.GameObjectTarget getNext() {
            return next;
        }
        public int getIndex() {
            return index_;
        }
    }

    public void print()
    {
        print("");
    }

    public void print(String spaces)
    {
        System.out.println(spaces + "tile {");

        for (Planet planet: planets_)
        {
            planet.print(spaces + "    ");
        }

        System.out.println("}");
    }
}

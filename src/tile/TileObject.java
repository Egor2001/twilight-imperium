package com.company;

class Unit{};
class Troop{};

public class TileObject {
    public void Invade() {}

    public void Allowed_to_invade() {}

    public Object[] My_neighbours() {
        return new Object[0];
    }

    Player owner_;
    Troop troop_;
    Tile my_tile;
}

class Planet extends TileObject {
    ResourceStore resource_store_;
}

class ResourceStore {

}

class Space extends TileObject{

}
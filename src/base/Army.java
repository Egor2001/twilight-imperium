package base;

import GroundForce.GroundForce;
import Ships.Ship;
import Structures.Structure;
import java.util.ArrayList;

import java.util.*;

public class Army implements Updatable {
    ArrayList<Ship> ships;
    ArrayList<Structure> structures;
    ArrayList<GroundForce> groundForces;
  
    public Army() {

    }

    @Override
    public void update() {}
}

package base.model;

import GroundForce.GroundForce;
import Ships.Ship;
import Structures.Structure;
import base.Updatable;

import java.util.*;

public class Army implements Updatable {
    Vector<Ship> ships;
    Vector<Structure> structures;
    Vector<GroundForce> groundForces;
  
    public Army() {

    }

    @Override
    public void update() {}
}

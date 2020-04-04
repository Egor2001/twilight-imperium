package base.model;

import ArmyUnits.Ships.Ship;
import ArmyUnits.Structures.Structure;
import ArmyUnits.GroundForce.GroundForce;
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

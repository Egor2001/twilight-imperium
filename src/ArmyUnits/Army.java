package ArmyUnits;

import ArmyUnits.GroundForce.GroundForce;
import ArmyUnits.Ships.Ship;
import ArmyUnits.Structures.Structure;
import base.Updatable;

import java.util.ArrayList;

public class Army implements Updatable {
    ArrayList<Ship> ships;
    ArrayList<Structure> structures;
    ArrayList<GroundForce> groundForces;

    public Army() {

    }

    @Override
    public void update() {}
}

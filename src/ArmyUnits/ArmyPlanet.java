package ArmyUnits;

import ArmyUnits.GroundForce.GroundForce;
import ArmyUnits.Ships.Ship;
import ArmyUnits.Structures.Structure;
import base.Updatable;

import java.util.ArrayList;

public class ArmyPlanet implements Updatable {
    ArrayList<Structure> structures;
    ArrayList<GroundForce> groundForces;

    public ArmyPlanet() {

    }

    @Override
    public void update() {}
}

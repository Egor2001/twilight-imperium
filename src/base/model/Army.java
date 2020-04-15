package base.model;

import ArmyUnits.GroundForceFactories.GroundForceAbstractFactory;
import ArmyUnits.ShipFactories.ShipAbstractFactory;
import ArmyUnits.Ships.Ship;
import ArmyUnits.StructureFactories.StructureAbstractFactory;
import ArmyUnits.Structures.Structure;
import ArmyUnits.GroundForce.GroundForce;
import ArmyUnits.Unit;
import base.Updatable;
import base.controller.HierarchyController.*;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class Army implements Updatable, UserAcceptable {
    private ArrayList<Ship> ships;
    private ArrayList<GroundForce> groundForces;
    private ArrayList<Structure> structures;
    private ShipAbstractFactory shipFactory;
    private GroundForceAbstractFactory groundForceFactory;
    private StructureAbstractFactory structureFactory;

    public void addShip(Ship ship) {
        ships.add(ship);
    }
    public void addGroundForce(GroundForce groundForce) {
        groundForces.add(groundForce);
    }
    public void addStructure(Structure structure) {
        structures.add(structure);
    }

    public void delShip(Ship ship) {
        ships.remove(ship);
    }
    public void delGroundForce(GroundForce groundForce) {
        groundForces.remove(groundForce);
    }
    public void delStructure(Structure structure) {
        structures.remove(structure);
    }

    public static class Target extends GameObjectTarget {
        Target() {
            super();
        }

        Target(Unit.Target unitTarget) {
            super(unitTarget);
        }
        Target(Structure.Target structureTarget) {
            super(structureTarget);
        }
    }

    public static class View implements Viewable {
        ArrayList<Viewable> unitsView;
        ArrayList<Viewable> structuresView;
        
        View(ArrayList<Viewable> unitsView, ArrayList<Viewable> structuresView) {
            this.unitsView = unitsView;
            this.structuresView = structuresView;
        }

        @Override
        public void display(Writer writer) throws IOException {
            writer.write("List army (" + this.toString() + ") units:\n");

            for (int i = 0; i < unitsView.size(); ++i) {
                writer.write(i + ": ");
                unitsView.get(i).display(writer);
            }

            writer.write("\nList army (" + this.toString() + ") structures:\n");
            for (int i = 0; i < structuresView.size(); ++i) {
                writer.write(i + ": ");
                structuresView.get(i).display(writer);
            }
        }
    }

    @Override
    public Viewable getView(UserAcceptable parent) {
        ArrayList<Viewable> unitsView = new ArrayList<Viewable>();
        ArrayList<Viewable> structuresView = new ArrayList<Viewable>();

        for (Ship ship: ships) {
            unitsView.add(ship.getView(this));
        }
        for (GroundForce groundForce: groundForces) {
            unitsView.add(groundForce.getView(this));
        }
        for (Structure structure: structures) {
            structuresView.add(structure.getView(this));
        }

        return new View(unitsView, structuresView);
    }

    @Override
    public Viewable getView(UserAcceptable parent, GameObjectTarget target) {
        if (target == null) {
            return getView(parent);
        }

        if (target instanceof Unit.Target) {
            if (target.getIndex() < ships.size()) {
                return ships.get(target.getIndex()).getView(this, target.getNext());
            } else if (target.getIndex() < ships.size() + groundForces.size()) {
                return groundForces.get(target.getIndex() - ships.size()).getView(this, target.getNext());
            }
        } else if (target instanceof Structure.Target) {
            if (target.getIndex() < structures.size()) {
                return structures.get(target.getIndex()).getView(this, target.getNext());
            }
        }

        return null;
    }

    @Override
    public Object getObject(GameObjectTarget target) {
        if (target == null) {
            return this;
        }

        if (target instanceof Unit.Target) {
            if (target.getIndex() < ships.size()) {
                return ships.get(target.getIndex()).getObject(target.getNext());
            } else if (target.getIndex() < ships.size() + groundForces.size()) {
                return groundForces.get(target.getIndex() - ships.size()).getObject(target.getNext());
            }
        } else if (target instanceof Structure.Target) {
            if (target.getIndex() < structures.size()) {
                return structures.get(target.getIndex()).getObject(target.getNext());
            }
        }

        return null;
    }
  
    public Army(String raceName) {
        ships = new ArrayList<Ship>();
        groundForces = new ArrayList<GroundForce>();
        structures = new ArrayList<Structure>();

    }

    @Override
    public void update() {}
}

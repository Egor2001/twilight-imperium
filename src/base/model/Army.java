package base.model;

import ArmyUnits.Ships.Ship;
import ArmyUnits.Structures.PDS;
import ArmyUnits.Structures.SpaceDock;
import ArmyUnits.GroundForce.GroundForce;
import base.Updatable;
import base.controller.HierarchyController;
import base.controller.HierarchyController.*;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class Army implements Updatable, UserAcceptable {
    private ArrayList<Ship> shipsList;
    private ArrayList<GroundForce> groundForcesList;
    private ArrayList<PDS> pdsList;
    private ArrayList<SpaceDock> spaceDocksList;

    public void addShip(Ship ship) {
        shipsList.add(ship);
    }
    public void addGroundForce(GroundForce groundForce) {
        groundForcesList.add(groundForce);
    }
    public void addPDS(PDS pds) {
        pdsList.add(pds);
    }
    public void addSpaceDock(SpaceDock spaceDock) {
        spaceDocksList.add(spaceDock);
    }

    public void delShip(Ship ship) {
        shipsList.remove(ship);
    }
    public void delGroundForce(GroundForce groundForce) {
        groundForcesList.remove(groundForce);
    }
    public void delPDS(PDS pds) {
        pdsList.remove(pds);
    }
    public void delSpaceDock(SpaceDock spaceDock) {
        spaceDocksList.remove(spaceDock);
    }

    public static class Target extends GameObjectTarget {
        Target() {
            super();
        }

        Target(Ship.Target shipTarget) {
            super(shipTarget);
        }
        Target(GroundForce.Target groundForceTarget) {
            super(groundForceTarget);
        }
        Target(PDS.Target pdsTarget) {
            super(pdsTarget);
        }
        Target(SpaceDock.Target spaceDockTarget) {
            super(spaceDockTarget);
        }
    }

    public static class View implements Viewable {
        ArrayList<Viewable> shipsView;
        ArrayList<Viewable> groundForcesView;
        ArrayList<Viewable> pdsView;
        ArrayList<Viewable> spaceDocksView;
        
        View(ArrayList<Viewable> shipsView, ArrayList<Viewable> groundForcesView, ArrayList<Viewable> pdsView, ArrayList<Viewable> spaceDocksView) {
            this.shipsView = shipsView;
            this.groundForcesView = groundForcesView;
            this.pdsView = pdsView;
            this.spaceDocksView = spaceDocksView;
        }

        @Override
        public void display(Writer writer) throws IOException {
            writer.write("List army (" + this.toString() + ") units:\n");

            Print(writer, shipsView);
            Print(writer, groundForcesView);
            Print(writer, pdsView);
            Print(writer, spaceDocksView);
        }

        private void Print(Writer writer, ArrayList<Viewable> unitView) throws IOException {
            for (int i = 0; i < unitView.size(); ++i) {
                unitView.get(i).display(writer);
                writer.write("." + i + "\n");
            }
        }
    }

    @Override
    public Viewable getView(UserAcceptable parent) {
        ArrayList<Viewable> shipsView = new ArrayList<>();
        ArrayList<Viewable> groundForcesView = new ArrayList<>();
        ArrayList<Viewable> pdsView = new ArrayList<>();
        ArrayList<Viewable> spaceDocksView = new ArrayList<>();

        for (Ship ship: shipsList) {
            shipsView.add(ship.getView(this));
        }
        for (GroundForce groundForce: groundForcesList) {
            groundForcesView.add(groundForce.getView(this));
        }
        for (PDS pds: pdsList) {
            pdsView.add(pds.getView(this));
        }
        for (SpaceDock spaceDock: spaceDocksList) {
            spaceDocksView.add(spaceDock.getView(this));
        }

        return new View(shipsView, groundForcesView, pdsView, spaceDocksView);
    }

    @Override
    public Viewable getView(UserAcceptable parent, GameObjectTarget target) {
        if (target == null) {
            return getView(parent);
        }

        if (target instanceof Ship.Target) {
            if (target.getIndex() < shipsList.size()) {
                return shipsList.get(target.getIndex()).getView(this, target.getNext());
            }
        } else if (target instanceof GroundForce.Target) {
            if (target.getIndex() < groundForcesList.size()) {
                return groundForcesList.get(target.getIndex()).getView(this, target.getNext());
            }
        } else if (target instanceof PDS.Target) {
            if (target.getIndex() < pdsList.size()) {
                return pdsList.get(target.getIndex()).getView(this, target.getNext());
            }
        } else if (target instanceof SpaceDock.Target) {
            if (target.getIndex() < spaceDocksList.size()) {
                return spaceDocksList.get(target.getIndex()).getView(this, target.getNext());
            }
        }

        return null;
    }

    @Override
    public Object getObject(GameObjectTarget target) {
        if (target == null) {
            return this;
        }

        if (target instanceof Ship.Target) {
            if (target.getIndex() < shipsList.size()) {
                return shipsList.get(target.getIndex()).getObject(target.getNext());
            }
        } else if (target instanceof GroundForce.Target) {
            if (target.getIndex() < groundForcesList.size()) {
                return groundForcesList.get(target.getIndex()).getObject(target.getNext());
            }
        } else if (target instanceof PDS.Target) {
            if (target.getIndex() < pdsList.size()) {
                return pdsList.get(target.getIndex()).getObject(target.getNext());
            }
        } else if (target instanceof SpaceDock.Target) {
            if (target.getIndex() < spaceDocksList.size()) {
                return spaceDocksList.get(target.getIndex()).getObject(target.getNext());
            }
        }

        return null;
    }
  
    public Army() {
        shipsList = new ArrayList<>();
        groundForcesList = new ArrayList<>();
        pdsList = new ArrayList<>();
        spaceDocksList = new ArrayList<>();
    }

    @Override
    public void update() {}
}

package player;

import base.user.GameObjectTarget;
import base.user.UserAcceptable;
import base.view.Viewable;
import player.units.Ships.Ship;
import player.units.Structures.PDS;
import player.units.Structures.SpaceDock;
import player.units.GroundForce.GroundForce;
import player.units.Unit;
import base.model.Updatable;

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
        public Target() {
            super();
        }

        public Target(GameObjectTarget next) {
            super(next);
        }
    }

    public class View implements Viewable {
        ArrayList<Viewable> shipsView;
        ArrayList<Viewable> groundForcesView;
        ArrayList<Viewable> pdsView;
        ArrayList<Viewable> spaceDocksView;
        
        public View(ArrayList<Viewable> shipsView, ArrayList<Viewable> groundForcesView, ArrayList<Viewable> pdsView, ArrayList<Viewable> spaceDocksView) {
            this.shipsView = shipsView;
            this.groundForcesView = groundForcesView;
            this.pdsView = pdsView;
            this.spaceDocksView = spaceDocksView;
        }

        @Override
        public void display(Writer writer) throws IOException {
            writer.write(toString());
        }

        public String toString(String start) {
            String[] className = this.getClass().getName().split("\\.");
            StringBuilder result = new StringBuilder(
                    start + "List army (" + className[className.length - 2] + "." + className[className.length - 1] + ") have" +
                    (shipsView.size() + groundForcesView.size() + pdsView.size() + spaceDocksView.size()) + "units:\n");

            result.append(print(shipsView, start));
            result.append(print(groundForcesView, start));
            result.append(print(pdsView, start));
            result.append(print(spaceDocksView, start));
            result.deleteCharAt(result.length() - 1);

            return result.toString();
        }
        public String toString() {
            return toString("");
        }

        private String print(ArrayList<Viewable> unitView, String start) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < unitView.size(); ++i) {
                result.append(unitView.get(i).toString(start + "    ")).append("\n");
            }

            return result.toString();
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
    public int getIndexUnit(Unit unit) {
        if (unit instanceof Ship) {
            return shipsList.indexOf(unit);
        } else if (unit instanceof GroundForce) {
            return groundForcesList.indexOf(unit);
        } else if (unit instanceof PDS) {
            return pdsList.indexOf(unit);
        } else if (unit instanceof SpaceDock) {
            return spaceDocksList.indexOf(unit);
        } else {
            return -1;
        }
    }

    @Override
    public void update() {}
}

package ArmyUnits;

import ArmyUnits.GroundForce.Infantry;
import ArmyUnits.Ships.*;
import ArmyUnits.Structures.PDS;
import ArmyUnits.Structures.SpaceDock;

public class FactoryUnit {
    String race;

    public FactoryUnit(String race) {
        this.race = race;
    }

    public Flagship createFlagship() {
        Flagship create = new Flagship();
        create.setAllFromJSONFile(race);
        return create;
    }
    public WarSun createWarSun() {
        WarSun create = new WarSun();
        create.setAllFromJSONFile(race);
        return create;
    }
    public Carrier createCarrier() {
        Carrier create = new Carrier();
        create.setAllFromJSONFile(race);
        return create;
    }
    public Cruiser createCruiser() {
        Cruiser create = new Cruiser();
        create.setAllFromJSONFile(race);
        return create;
    }
    public Destroyer createDestroyer() {
        Destroyer create = new Destroyer();
        create.setAllFromJSONFile(race);
        return create;
    }
    public Dreadnought createDreadnought() {
        Dreadnought create = new Dreadnought();
        create.setAllFromJSONFile(race);
        return create;
    }
    public Fighter createFighter() {
        Fighter create = new Fighter();
        create.setAllFromJSONFile(race);
        return create;
    }

    public Infantry createInfantry() {
        Infantry create = new Infantry();
        create.setAllFromJSONFile(race);
        return create;
    }

    public PDS createPDS() {
        PDS create = new PDS();
        create.setAllFromJSONFile(race);
        return create;
    }
    public SpaceDock createSpaceDock() {
        SpaceDock create = new SpaceDock();
        create.setAllFromJSONFile(race);
        return create;
    }
}

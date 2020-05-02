package player;

import player.units.GroundForce.Infantry;
import player.units.Ships.*;
import player.units.Structures.PDS;
import player.units.Structures.SpaceDock;
import player.Race;

public class FactoryUnit {
    String nameRace;

    public FactoryUnit(String race) {
        this.nameRace = race;
    }

    public Flagship createFlagship(Race race) {
        Flagship create = new Flagship(race);
        create.setAllFromJSONFile(nameRace);
        return create;
    }
    public WarSun createWarSun(Race race) {
        WarSun create = new WarSun(race);
        create.setAllFromJSONFile(nameRace);
        return create;
    }
    public Carrier createCarrier(Race race) {
        Carrier create = new Carrier(race);
        create.setAllFromJSONFile(nameRace);
        return create;
    }
    public Cruiser createCruiser(Race race) {
        Cruiser create = new Cruiser(race);
        create.setAllFromJSONFile(nameRace);
        return create;
    }
    public Destroyer createDestroyer(Race race) {
        Destroyer create = new Destroyer(race);
        create.setAllFromJSONFile(nameRace);
        return create;
    }
    public Dreadnought createDreadnought(Race race) {
        Dreadnought create = new Dreadnought(race);
        create.setAllFromJSONFile(nameRace);
        return create;
    }
    public Fighter createFighter(Race race) {
        Fighter create = new Fighter(race);
        create.setAllFromJSONFile(nameRace);
        return create;
    }

    public Infantry createInfantry(Race race) {
        Infantry create = new Infantry(race);
        create.setAllFromJSONFile(nameRace);
        return create;
    }

    public PDS createPDS(Race race) {
        PDS create = new PDS(race);
        create.setAllFromJSONFile(nameRace);
        return create;
    }
    public SpaceDock createSpaceDock(Race race) {
        SpaceDock create = new SpaceDock(race);
        create.setAllFromJSONFile(nameRace);
        return create;
    }
}

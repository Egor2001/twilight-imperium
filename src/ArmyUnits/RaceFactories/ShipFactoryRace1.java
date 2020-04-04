package ArmyUnits.RaceFactories;

import ArmyUnits.Ships.*;


public class ShipFactoryRace1 implements ShipAbstractFactory {
    String race = "Race1";
    @Override
    public Flagship createFlagship() {
        Flagship create = new Flagship();
        create.setAllFromJSONFile(race);

        return create;
    }

    @Override
    public WarSun createWarSun() {
        return null;
    }

    @Override
    public Carrier createCarrier() {
        return null;
    }

    @Override
    public Cruiser createCruiser() {
        return null;
    }

    @Override
    public Destroyer createDestroyer() {
        return null;
    }

    @Override
    public Dreadnought createDreadnought() {
        return null;
    }

    @Override
    public Fighter createFighter() {
        return new Fighter();
    }
}

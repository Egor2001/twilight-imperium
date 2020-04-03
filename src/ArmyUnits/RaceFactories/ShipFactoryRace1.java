package ArmyUnits.RaceFactories;

import ArmyUnits.Ships.*;

public class ShipFactoryRace1 implements ShipAbstractFactory {
    @Override
    public Flagship createFlagship() {
        Flagship create = new Flagship();//видимо FlagshipRace1

        //считываем из файла
        create.setCost(0);
        create.setCombatValue(0);
        create.setMoveValue(0);
        create.setCapacityValue(0);

        create.setCanSustainDamaged(false);
        create.setSpaceCannon(0, 0);
        create.setBombardment(0, 0);
        create.setAntiFighterBarrage(0, 0);

        return create;
    }

    @Override
    public WarSun createWarSum() {
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

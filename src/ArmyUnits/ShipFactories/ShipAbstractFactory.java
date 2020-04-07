package ArmyUnits.ShipFactories;

import ArmyUnits.Ships.*;

public interface ShipAbstractFactory {
    Carrier createCarrier();
    Cruiser createCruiser();
    Destroyer createDestroyer();
    Dreadnought createDreadnought();
    Fighter createFighter();
    Flagship createFlagship();
    WarSun createWarSun();
}

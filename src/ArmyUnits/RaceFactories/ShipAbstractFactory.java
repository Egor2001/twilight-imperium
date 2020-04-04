package ArmyUnits.RaceFactories;

import ArmyUnits.Ships.*;

import java.io.FileNotFoundException;

public interface ShipAbstractFactory {
    Carrier createCarrier();
    Cruiser createCruiser();
    Destroyer createDestroyer();
    Dreadnought createDreadnought();
    Fighter createFighter();
    Flagship createFlagship();
    WarSun createWarSun();
}

package ArmyUnits.RaceFactories;

import ArmyUnits.Ships.Fighter;
import ArmyUnits.Ships.Flagship;

public interface ShipFactory {
    Flagship CreateFlagship();
    Fighter CreateFighter();
}

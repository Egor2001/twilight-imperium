package RaceFactories;

import Ships.Fighter;
import Ships.Flagship;

public interface ShipFactory {
    Flagship CreateFlagship();
    Fighter CreateFighter();
}

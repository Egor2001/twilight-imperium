package ArmyUnits.RaceFactories;

import ArmyUnits.Structures.PDS;
import ArmyUnits.Structures.SpaceDock;

public interface StructureAbstractFactory {
    PDS createPDS();
    SpaceDock createSpaceDock();
}

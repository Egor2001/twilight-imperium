package ArmyUnits.StructureFactories;

import ArmyUnits.Structures.PDS;
import ArmyUnits.Structures.SpaceDock;

public interface StructureAbstractFactory {
    PDS createPDS();
    SpaceDock createSpaceDock();
}

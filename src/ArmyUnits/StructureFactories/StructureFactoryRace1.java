package ArmyUnits.StructureFactories;

import ArmyUnits.Structures.PDS;
import ArmyUnits.Structures.SpaceDock;

public class StructureFactoryRace1 implements StructureAbstractFactory {
    String race = "Race1";

    @Override
    public PDS createPDS() {
        PDS create = new PDS();
        create.setAllFromJSONFile(race);
        return create;
    }

    @Override
    public SpaceDock createSpaceDock() {
        return null;
    }
}

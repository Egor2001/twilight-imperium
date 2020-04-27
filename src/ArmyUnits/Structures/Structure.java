package ArmyUnits.Structures;

import ArmyUnits.LoaderFromJSON;
import ArmyUnits.Unit;
import Races.Race;
import base.Updatable;
import base.controller.HierarchyController.*;

import java.io.IOException;
import java.io.Writer;

public abstract class Structure extends Unit {
    public Structure(Race race) {
        super(race);
    }
}

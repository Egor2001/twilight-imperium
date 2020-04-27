package Races;

import ArmyUnits.FactoryUnit;
import ArmyUnits.GroundForce.GroundForce;
import ArmyUnits.GroundForce.Infantry;
import ArmyUnits.Ships.Ship;
import ArmyUnits.Structures.PDS;
import ArmyUnits.Structures.SpaceDock;

import java.lang.reflect.InvocationTargetException;

public abstract class Race {
    private FactoryUnit factoryUnit;

    public Race (String name) {
        factoryUnit = new FactoryUnit(name);
    }

    public Ship addShip(String name) {
        try {
            java.lang.reflect.Method method = factoryUnit.getClass().getMethod("create" + name);
            return (Ship) method.invoke(factoryUnit, this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PDS addPDS() {
        return factoryUnit.createPDS(this);
    }
    public SpaceDock addSpaceDock() {
        return factoryUnit.createSpaceDock(this);
    }
    public Infantry addInfantry() {
        return factoryUnit.createInfantry(this);
    }
}

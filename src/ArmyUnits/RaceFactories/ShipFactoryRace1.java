package ArmyUnits.RaceFactories;

import ArmyUnits.Ships.Fighter;
import ArmyUnits.Ships.Flagship;

public class ShipFactoryRace1 implements ShipFactory {
    @Override
    public Flagship CreateFlagship() {
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
    public Fighter CreateFighter() {
        return new Fighter();
    }
}

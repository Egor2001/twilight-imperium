package base;

import ArmyUnits.ShipFactories.ShipFactoryRace1;
import ArmyUnits.Ships.Flagship;

public class Main {
    public static void main(String[] args) {

        ShipFactoryRace1 F1 = new ShipFactoryRace1();
        Flagship Flagship1 = F1.createFlagship();
        System.out.print(Flagship1.getCost());

        /*GameController gameController = GameController.getInstance();
        gameController.gameInit();
        gameController.gameLoop();*/
    }
}

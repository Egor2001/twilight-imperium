package base;

import ArmyUnits.RaceFactories.ShipFactoryRace1;
import ArmyUnits.Ships.Flagship;

public class Main {
    public static void main(String[] args) {
        GameController gameController = GameController.getInstance();
        gameController.gameInit();
        gameController.gameLoop();
    }
}

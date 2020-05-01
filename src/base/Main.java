package base;

import base.controller.fight.SpaceCombatController;

public class Main {

    public static void main(String[] args) {
        GameController gameController = GameController.getInstance();
        gameController.start();
    }
}

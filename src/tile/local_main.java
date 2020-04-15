package tile;

import ArmyUnits.ShipFactories.ShipFactoryRace1;
import ArmyUnits.Ships.Flagship;
import base.controller.GameController;
import base.controller.HierarchyController;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class local_main {
    public static void main(String[] args) {
        Planet planet = new Planet("Abyz");

        planet.print();
    }
}
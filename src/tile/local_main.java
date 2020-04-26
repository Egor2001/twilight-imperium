package tile;

//import ArmyUnits.ShipFactories.ShipFactoryRace1;
import ArmyUnits.Ships.Flagship;
import base.controller.GameController;
import base.controller.HierarchyController;

import tile.Tile;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Arrays;

public class local_main {
    public static void main(String[] args) throws Exception {
        Tile tile1 = new Tile(new ArrayList<String>(Arrays.asList("Abyz", "Arinam", "Arnor")));
        Tile tile2 = new Tile(new ArrayList<String>(Arrays.asList("Lodor", "Meer")));

        Board board = new Board();

        board.AddTile(tile1);
        board.AddTile(tile2);

        board.AddBond(0, 1);
        Board.Target tg = new Board.Target(new Tile.Target(null, 1));

        ((Tile) board.getObject(tg.getNext())).print();
    }
}
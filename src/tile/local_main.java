package tile;

import ArmyUnits.FactoryUnit;
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
        FactoryUnit factory = new FactoryUnit("Arborec");
        TileArmyController controller = new TileArmyController();
        Board board = new Board(controller);

        controller.add(factory.createCarrier(), board.tiles_.get(2).space_);
        controller.add(factory.createCarrier(), board.tiles_.get(1).space_);
        controller.add(factory.createDestroyer(), board.tiles_.get(1).space_);

        System.out.println(board.tiles_.size());
        for (int i = 0; i < 4; ++i)
            board.tiles_.get(i).space_.My_neighbours();

        System.out.println(board.getView(null));
    }
}
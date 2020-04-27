package tile;

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
        Board board = new Board(true);

        System.out.println(board.tiles_.size());
        for (int i = 0; i < 4; ++i)
            board.tiles_.get(i).space_.My_neighbours();
    }
}
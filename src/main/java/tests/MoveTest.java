package tests;

import base.GameController;
import base.user.GameObjectTarget;
import base.user.HierarchyManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import player.Player;
import junit.framework.TestCase;
import board.Board;
import board.TileArmyManager;

import java.io.*;
import java.util.ArrayList;

public class MoveTest extends Assert {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testMove () {
        try {
            DoTestFromFile();
        }
        catch (Exception e)
        {
            fail();
        }
    }

    @Test
    public void testComplexMove() {

    }

    private String DoTestFromFile() throws FileNotFoundException {
        File input_file = new File("tests.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("out_texst.txt", true);


        GameController gameController = GameController.getInstance( new FileInputStream(input_file), new PrintStream(fileOutputStream));
        gameController.start();

        return "";
    }
}
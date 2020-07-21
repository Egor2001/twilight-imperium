package base;

import java.util.ArrayList;

/**
 * The main class
 * @author geome_try
 * @see GameController
 */

public class Main {

    /**
     * The entry point function
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        GameController.getInstance().start();
    }
}

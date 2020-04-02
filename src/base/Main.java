package base;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, world!");

        GameController gameController = GameController.getInstance();
        gameController.gameInit();
        gameController.gameLoop();
    }
}

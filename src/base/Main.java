package base;

public class Main {
    public static void main(String[] args) {
        GameController gameController = GameController.getInstance();
        gameController.gameInit();
        gameController.gameLoop();
    }
}

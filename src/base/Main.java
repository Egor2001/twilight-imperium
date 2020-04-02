package base;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, world!");

        CGameController gameController = CGameController.getInstance();
        gameController.gameInit();
        gameController.gameLoop();
    }
}

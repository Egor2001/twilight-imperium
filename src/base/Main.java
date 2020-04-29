package base;

public class Main {

    public static void main(String[] args) {
        //Player player = new Player("a", "Winnu");
        GameController gameController = GameController.getInstance();
        gameController.start();
    }
}

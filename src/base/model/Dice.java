package base.model;

import java.util.Random;

public class Dice {
    Random random;

    public Dice() {
        random = new Random();
    }

    public int getValue() {
        return random.nextInt() % 10 + 1;
    }
}

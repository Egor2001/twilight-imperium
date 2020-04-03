package base;

import ArmyUnits.Army;

//TODO: to implement IUpdatable
public class Player implements Updatable {

    private String name;
    private Army army;

    public Player(String name, Army army) {
        this.name = name;
        this.army = army;
    }

    public final String getName() {
        return name;
    }

    public final Army getArmy() {
        return army;
    }

    @Override
    public void update() {
        army.update();
    }
}

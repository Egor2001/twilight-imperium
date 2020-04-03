package base;

import ArmyUnits.ArmyPlanet;

//TODO: to implement IUpdatable
public class Player implements Updatable {

    private String name;
    private ArmyPlanet army;

    public Player(String name, ArmyPlanet army) {
        this.name = name;
        this.army = army;
    }

    public final String getName() {
        return name;
    }

    public final ArmyPlanet getArmy() {
        return army;
    }

    @Override
    public void update() {
        army.update();
    }
}

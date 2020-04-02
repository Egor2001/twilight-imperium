package base;

//TODO: to implement IUpdatable
public class CPlayer implements IUpdatable {

    private String name;
    private CArmy army;

    public CPlayer(String name, CArmy army) {
        this.name = name;
        this.army = army;
    }

    public final String getName() {
        return name;
    }

    public final CArmy getArmy() {
        return army;
    }

    @Override
    public void update() {
        army.update();
    }
}

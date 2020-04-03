package ArmyUnits.GroundForce;

import base.Unit;
import base.Updatable;

public abstract class GroundForce implements Unit, Updatable {
    private int combatValue;
    private int cost;

    public int getCost() {
        return cost;
    }
    public boolean canHit(int diceValue) {
        return diceValue >= combatValue;
    }

    public void setCombatValue(int newCombatValue) {
        combatValue = newCombatValue;
    }
    public void setCost(int newCost) {
        cost = newCost;
    }

    @Override
    public void update() {

    }
}

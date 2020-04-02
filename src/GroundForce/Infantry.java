package GroundForce;

public class Infantry extends GroundForce {
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
}

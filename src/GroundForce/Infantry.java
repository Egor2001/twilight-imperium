package GroundForce;

public class Infantry extends GroundForce {
    private int CombatValue_;
    private int Cost_;

    public int getCost() {
        return Cost_;
    }
    public boolean canHit(int DiceValue) {
        return DiceValue >= CombatValue_;
    }

    public void setCombatValue(int CombatValue) {
        CombatValue_ = CombatValue;
    }
    public void setCost(int Cost) {
        Cost_ = Cost;
    }
}

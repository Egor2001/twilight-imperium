package GroundForce;

import com.twilight.Unit;
import com.twilight.Updatable;

public abstract class GroundForce implements Unit, Updatable {
    private int CombatValue;
    private int Cost;

    public boolean CanHit(int DiceValue) {
        return DiceValue >= CombatValue;
    }

    @Override
    public void Update() {

    }
}

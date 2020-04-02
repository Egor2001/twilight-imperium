package com.twilight;

public class GroundForces implements Unit, Updatable {
    private int CombatValue;
    private int Cost;

    public boolean CanHit(int DiceValue) {
        return DiceValue >= CombatValue;
    }

    @Override
    public void Upgrade() {

    }

    @Override
    public void Update() {

    }
}

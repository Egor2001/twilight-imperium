package com.twilight;

public class Flagship extends Ship {
    private boolean Damaged;

    Flagship() {
        Damaged = false;
    }

    public boolean IsDamaged() {
        return Damaged;
    }
    public void GetDamaged() {
        Damaged = true;
    }
}

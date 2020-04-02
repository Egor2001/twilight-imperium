package com.twilight;

public class ShipBuilder implements Builder {
    private Ship ship;

    public void reset() {
        ship = new Ship();
    }

    public void setMoveValue(int MoveValue) {
        ship.setMoveValue(MoveValue);
    }
    public void setCapacityValue(int CapacityValue) {
        ship.setCapacityValue(CapacityValue);
    }
    public void setCombatValue(int CombatValue) {
        ship.setCombatValue(CombatValue);
    }
    public void setCost(int Cost) {
        ship.setCost(Cost);
    }

    public void setCanDamaged(boolean CanDamaged) {
        ship.setCanDamaged(CanDamaged);
    }

    public void setSpaceCannon(int DiceValue, int NumDices) {
        ship.setSpaceCannon(DiceValue, NumDices);
    }
    public void setBombardment(int DiceValue, int NumDices) {
        ship.setBombardment(DiceValue, NumDices);
    }
    public void setAntiFighterBarrage(int DiceValue, int NumDices) {
        ship.setAntiFighterBarrage(DiceValue, NumDices);
    }

    public Ship getShip() {
        return ship;
    }
}

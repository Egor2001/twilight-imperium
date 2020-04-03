package Ships;

import base.model.Unit;
import base.Updatable;

public abstract class Ship implements Unit, Updatable {
    private int moveValue;
    private int capacityValue;
    private int combatValue;
    private int cost;

    private boolean canSustainDamaged = false;
    private boolean damaged;

    private int[] spaceCannon = new int[2];
    private int[] bombardment = new int[2];
    private int[] antiFighterBarrage = new int[2];

    public int getMoveValue() {
        return moveValue;
    }
    public int getCapacityValue() {
        return capacityValue;
    }
    public int getCost() {
        return cost;
    }

    public boolean canHit(int diceValue) {
        return diceValue >= combatValue;
    }

    public boolean isDamaged() {
        return damaged;
    }
    public boolean canSustainDamaged() {
        return canSustainDamaged;
    }
    public void takeDamaged() {
        damaged = true;
    }

    public int getNumberOfDicesForSpaceCannon() {
        return spaceCannon[1];
    }
    public boolean canHitFromSpaceCannon(int diceValue) {
        return diceValue >= spaceCannon[0];
    }

    public int getNumberOfDicesForBombardment() {
        return bombardment[1];
    }
    public boolean canHitFromBombardment(int diceValue) {
        return diceValue >= bombardment[0];
    }

    public int getNumberOfDicesForAntiFighterBarrage() {
        return antiFighterBarrage[1];
    }
    public boolean canHitFromAntiFighterBarrage(int diceValue) {
        return diceValue >= antiFighterBarrage[0];
    }

    @Override
    public void update() {}

    public void setMoveValue(int newMoveValue) {
        moveValue = newMoveValue;
    }
    public void setCapacityValue(int newCapacityValue) {
        capacityValue = newCapacityValue;
    }
    public void setCombatValue(int newCombatValue) {
        combatValue = newCombatValue;
    }
    public void setCost(int newCost) {
        cost = newCost;
    }

    public void setCanSustainDamaged(boolean canDamaged) {
        canSustainDamaged = canDamaged;
    }

    public void setSpaceCannon(int diceValue, int numDices) {
        spaceCannon[0] = diceValue;
        spaceCannon[1] = numDices;
    }
    public void setBombardment(int diceValue, int numDices) {
        bombardment[0] = diceValue;
        bombardment[1] = numDices;
    }
    public void setAntiFighterBarrage(int diceValue, int numDices) {
        antiFighterBarrage[0] = diceValue;
        antiFighterBarrage[1] = numDices;
    }
}

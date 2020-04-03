package ArmyUnits.Ships;

import base.Unit;
import base.Updatable;

public abstract class Ship implements Unit, Updatable {
    private int moveValue = 0;
    private int capacityValue = 0;
    private int combatValue;
    private int cost;

    private boolean canSustainDamaged = false;
    private boolean damaged = false;

    private int spaceCannonDiceValue = 10;
    private int spaceCannonNumDices = 0;
    private int bombardmentDiceValue = 10;
    private int bombardmentNumDices = 0;
    private int antiFighterBarrageDiceValue = 10;
    private int antiFighterBarrageNumDices = 0;

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
        return spaceCannonNumDices;
    }
    public boolean canHitFromSpaceCannon(int diceValue) {
        return diceValue >= spaceCannonDiceValue;
    }

    public int getNumberOfDicesForBombardment() {
        return bombardmentNumDices;
    }
    public boolean canHitFromBombardment(int diceValue) {
        return diceValue >= bombardmentDiceValue;
    }

    public int getNumberOfDicesForAntiFighterBarrage() {
        return antiFighterBarrageDiceValue;
    }
    public boolean canHitFromAntiFighterBarrage(int diceValue) {
        return diceValue >= antiFighterBarrageNumDices;
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
        spaceCannonDiceValue = diceValue;
        spaceCannonNumDices = numDices;
    }
    public void setBombardment(int diceValue, int numDices) {
        bombardmentDiceValue = diceValue;
        bombardmentNumDices = numDices;
    }
    public void setAntiFighterBarrage(int diceValue, int numDices) {
        antiFighterBarrageDiceValue = diceValue;
        antiFighterBarrageNumDices = numDices;
    }
}

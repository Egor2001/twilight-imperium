package ArmyUnits.Ships;

import ArmyUnits.Unit;
import Races.Race;
import base.controller.HierarchyController;
import org.json.*;

import java.io.IOException;
import java.io.Writer;

public abstract class Ship extends Unit {
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
    public boolean hasSpaceCannon() {
        return spaceCannonNumDices > 0;
    }

    public int getNumberOfDicesForBombardment() {
        return bombardmentNumDices;
    }
    public boolean canHitFromBombardment(int diceValue) {
        return diceValue >= bombardmentDiceValue;
    }
    public boolean hasBombardment() {
        return bombardmentNumDices > 0;
    }

    public int getNumberOfDicesForAntiFighterBarrage() {
        return antiFighterBarrageDiceValue;
    }
    public boolean canHitFromAntiFighterBarrage(int diceValue) {
        return diceValue >= antiFighterBarrageNumDices;
    }
    public boolean hasAntiFighterBarrage() {
        return antiFighterBarrageNumDices > 0;
    }

    @Override
    public boolean canFightInSpace() {
        return true;
    }

    @Override
    public void update() {}

    public Ship(Race race) {
        super(race);
    }

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

    @Override
    public void setAllFromJSON(JSONObject object) {
        moveValue = (int) object.get("moveValue");
        capacityValue = (int) object.get("capacityValue");
        combatValue = (int) object.get("combatValue");
        cost = (int) object.get("cost");

        canSustainDamaged = (boolean) object.get("canSustainDamaged");
        damaged = (boolean) object.get("damaged");

        spaceCannonDiceValue = (int) object.get("spaceCannonDiceValue");
        spaceCannonNumDices = (int) object.get("spaceCannonNumDices");
        bombardmentDiceValue = (int) object.get("bombardmentDiceValue");
        bombardmentNumDices = (int) object.get("bombardmentNumDices");
        antiFighterBarrageDiceValue = (int) object.get("antiFighterBarrageDiceValue");
        antiFighterBarrageNumDices = (int) object.get("antiFighterBarrageNumDices");
    }

    public static class Target extends HierarchyController.GameObjectTarget {
        public Target() {
            super();
        }
        public Target(HierarchyController.GameObjectTarget next, int index) {
            super(next, index);
        }
    }

    @Override
    public String getInfo(String start) {
        StringBuilder result = new StringBuilder(start + "Cost: " + cost + "\n" +
                start + "Combat: " + combatValue + "\n" + start + "Move: " + moveValue + "\n");

        if (capacityValue > 0) {
            result.append(start).append("Capacity: ").append(capacityValue).append("\n");
        }

        if (canSustainDamaged) {
            result.append(start).append("It can sustain damage and now it is");
            if (!damaged) {
                result.append("n't");
            }
            result.append(" damaged\n");
        }

        if (bombardmentNumDices > 0) {
            result.append(start).append("Bombardment: ").append(bombardmentNumDices).append("(x").append(bombardmentNumDices).append(")\n");
        }

        if (spaceCannonNumDices > 0) {
            result.append(start).append("Space Cannon: ").append(spaceCannonDiceValue).append("(x").append(spaceCannonNumDices).append(")\n");
        }

        if (antiFighterBarrageNumDices > 0) {
            result.append(start).append("Anti-fighter barrage: ").append(antiFighterBarrageDiceValue).
                    append("(x").append(antiFighterBarrageNumDices).append(")\n");
        }

        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }
}

package player.units.Ships;

import base.user.GameObjectTarget;
import player.units.Unit;
import player.Race;
import org.json.*;

public abstract class Ship extends Unit {
    private int moveValue;
    private int capacityValue;
    private int combatValue;
    private int combatNumDices = 1;
    private int cost;
    private int costNumUnits = 1;

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
    public int getCostNumUnits() {
        return costNumUnits;
    }

    public boolean canHit(int diceValue) {
        return diceValue >= combatValue;
    }
    public int getCombatNumDices() {
        return combatNumDices;
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
    public void setCombatNumDices(int newCombatNumDices) {
        combatNumDices = newCombatNumDices;
    }
    public void setCost(int newCost) {
        cost = newCost;
    }
    public void setCostNumUnits(int newCostNumUnits) {
        costNumUnits = newCostNumUnits;
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
        moveValue = object.getInt("moveValue");
        capacityValue = object.getInt("capacityValue");
        combatValue = object.getInt("combatValue");
        combatNumDices = object.getInt("combatNumDices");
        cost = object.getInt("cost");
        costNumUnits = object.getInt("costNumUnits");

        setCanSustainDamaged(object.getBoolean("canSustainDamaged"));
        setDamaged(object.getBoolean("damaged"));

        spaceCannonDiceValue = object.getInt("spaceCannonDiceValue");
        spaceCannonNumDices = object.getInt("spaceCannonNumDices");
        bombardmentDiceValue = object.getInt("bombardmentDiceValue");
        bombardmentNumDices = object.getInt("bombardmentNumDices");
        antiFighterBarrageDiceValue = object.getInt("antiFighterBarrageDiceValue");
        antiFighterBarrageNumDices = object.getInt("antiFighterBarrageNumDices");
    }

    public static class Target extends GameObjectTarget {
        public Target() {
            super();
        }
        public Target(GameObjectTarget next, int index) {
            super(next, index);
        }
    }

    @Override
    public String getInfo(String start) {
        StringBuilder result = new StringBuilder(start + "Cost: " + cost);
        if (costNumUnits > 1) {
            result.append(" (x").append(costNumUnits).append(")");
        }
        result.append("\n");

        result.append(start).append("Combat: ").append(combatValue);
        if (combatNumDices > 1) {
            result.append(" (x").append(combatNumDices).append(")");
        }
        result.append("\n");

        result.append(start).append("Move: ").append(moveValue).append("\n");

        if (capacityValue > 0) {
            result.append(start).append("Capacity: ").append(capacityValue).append("\n");
        }

        if (canSustainDamaged()) {
            result.append(start).append("It can sustain damage and now it is");
            if (!isDamaged()) {
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

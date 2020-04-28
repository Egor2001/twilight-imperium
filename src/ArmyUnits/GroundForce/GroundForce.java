package ArmyUnits.GroundForce;

import ArmyUnits.Unit;

import Races.Race;
import base.controller.HierarchyController;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Writer;

public abstract class GroundForce extends Unit {
    private int combatValue;
    private int combatNumDices = 1;
    private int cost;
    private int costNumUnits = 1;
    private boolean canFightSpace;

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

    @Override
    public void setAllFromJSON(JSONObject object) {
        combatValue = object.getInt("combatValue");
        combatNumDices = object.getInt("combatNumDices");
        cost = object.getInt("cost");
        costNumUnits = object.getInt("costNumUnits");

        canFightSpace = object.getBoolean("canFlightSpace");
    }

    @Override
    public boolean canFightInSpace() {
        return canFightSpace;
    }

    @Override
    public void update() {

    }

    public GroundForce(Race race) {
        super(race);
    }

    public static class Target extends HierarchyController.GameObjectTarget {
        public Target() {
            super();
        }
        public Target(HierarchyController.GameObjectTarget next) {
            super(next);
        }
        public Target(int index) {
            super(index);
        }
        public Target(HierarchyController.GameObjectTarget next, int index) {
            super(next, index);
        }
    }

    @Override
    public String getInfo(String start) {
        String result = "";

        result += start + "Cost: " + cost;
        if (costNumUnits > 1) {
            result += " (x" + costNumUnits + ")";
        }
        result += "\n";

        result += start + "Combat: " + combatValue;
        if (combatNumDices > 1) {
            result += " (x" + combatNumDices + ")";
        }

        return result;
    }
}

package ArmyUnits.GroundForce;

import ArmyUnits.Unit;

import base.controller.HierarchyController;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Writer;

public abstract class GroundForce implements Unit {
    private int combatValue;
    private int cost;
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
        combatValue = (int) object.get("combatValue");
        cost = (int) object.get("cost");
        canFightSpace = (boolean) object.get("canFlightSpace");
    }

    @Override
    public boolean canFightInSpace() {
        return canFightSpace;
    }

    @Override
    public void update() {

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
    public void printInfo(Writer writer) throws IOException {
        writer.write("Cost: " + cost + "\n");
        writer.write("Combat: " + combatValue + "\n");
    }
}

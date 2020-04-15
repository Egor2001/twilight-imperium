package ArmyUnits.GroundForce;

import ArmyUnits.Unit;

import base.controller.HierarchyController;
import org.json.JSONObject;

public abstract class GroundForce implements Unit {
    private int combatValue;
    private int cost;

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
    }


    @Override
    public void update() {

    }

    public class Target extends HierarchyController.GameObjectTarget {
        Target() {
            super();
        }
        Target(int index) {
            super(index);
        }
    }
}

package ArmyUnits.GroundForce;

import ArmyUnits.Unit;
import base.Updatable;
import org.json.JSONObject;

public abstract class GroundForce implements Unit, Updatable {
    private int combatValue;
    private int cost;

    @Override
    public int getCost() {
        return cost;
    }
    @Override
    public boolean canHit(int diceValue) {
        return diceValue >= combatValue;
    }

    @Override
    public void setCombatValue(int newCombatValue) {
        combatValue = newCombatValue;
    }
    @Override
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
}

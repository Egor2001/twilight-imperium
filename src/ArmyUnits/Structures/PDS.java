package ArmyUnits.Structures;

import ArmyUnits.Unit;
import Races.Race;
import base.controller.HierarchyController;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Writer;

public class PDS extends Structure {
    private int spaceCannonDiceValue;
    private int spaceCannonNumDices;
    private boolean planetaryShield;

    public int getNumberOfDicesForSpaceCannon() {
        return spaceCannonNumDices;
    }
    public boolean canHitFromSpaceCannon(int diceValue) {
        return diceValue >= spaceCannonDiceValue;
    }
    public void setSpaceCannon(int diceValue, int numDices) {
        spaceCannonDiceValue = diceValue;
        spaceCannonNumDices = numDices;
    }

    public void setPlanetaryShield(boolean isPlanetaryShield) {
        planetaryShield = isPlanetaryShield;
    }
    public boolean getPlanetaryShield() {
        return planetaryShield;
    }

    @Override
    public void setAllFromJSON(JSONObject object) {
        spaceCannonDiceValue = (int) object.get("spaceCannonDiceValue");
        spaceCannonNumDices = (int) object.get("spaceCannonNumDices");
        planetaryShield = (boolean) object.get("planetaryShield");
    }

    @Override
    public boolean canFightInSpace() {
        return false;
    }

    @Override
    public void update() {

    }

    public PDS(Race race) {
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
    public String getInfo(String start)  {
        String result = "";

        if (spaceCannonNumDices > 0) {
            result += start + "Space Cannon: " + spaceCannonDiceValue + "(x" + spaceCannonNumDices + ")\n";
        }

        result += start + "Planetary Shield ";
        if (planetaryShield) {
            result += "on";
        } else {
            result += "off";
        }

        return result;
    }
}

package ArmyUnits.Structures;

import ArmyUnits.Unit;
import base.controller.HierarchyController;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Writer;

public class PDS implements Structure {
    private int spaceCannonDiceValue = 10;
    private int spaceCannonNumDices = 0;
    private boolean planetaryShield = false;

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
    public void update() {

    }

    public static class Target extends HierarchyController.GameObjectTarget {
        Target() {
            super();
        }
        Target(int index) {
            super(index);
        }
    }


    @Override
    public void printInfo(Writer writer) throws IOException {
        if (spaceCannonNumDices > 0) {
            writer.write("Space Cannon: " + spaceCannonDiceValue + "(x" + spaceCannonNumDices + ")\n");
        }
        writer.write("Planetary Shield ");
        if (planetaryShield) {
            writer.write("on\n");
        } else {
            writer.write("off\n");
        }
    }
}

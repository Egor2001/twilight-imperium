package ArmyUnits.Structures;

import org.json.JSONObject;

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
}

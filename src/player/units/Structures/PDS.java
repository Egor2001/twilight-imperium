package player.units.Structures;

import base.user.GameObjectTarget;
import player.Race;
import org.json.JSONObject;

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
        spaceCannonDiceValue = object.getInt("spaceCannonDiceValue");
        spaceCannonNumDices = object.getInt("spaceCannonNumDices");
        planetaryShield = object.getBoolean("planetaryShield");
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

    public static class Target extends GameObjectTarget {
        public Target() {
            super();
        }
        public Target(GameObjectTarget next) {
            super(next);
        }
        public Target(int index) {
            super(index);
        }
        public Target(GameObjectTarget next, int index) {
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

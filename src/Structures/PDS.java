package Structures;

public class PDS extends Structure {
    private int spaceCannonDiceValue = 10;
    private int spaceCannonNumDices = 0;
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
}

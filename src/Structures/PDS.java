package Structures;

public class PDS extends Structure {
    private int[] spaceCannon_ = new int[2];
    private boolean planetaryShield;

    public int getNumberOfDicesForSpaceCannon() {
        return spaceCannon_[1];
    }
    public boolean canHitFromSpaceCannon(int diceValue) {
        return diceValue >= spaceCannon_[0];
    }
    public void setSpaceCannon(int diceValue, int numDices) {
        spaceCannon_[0] = diceValue;
        spaceCannon_[1] = numDices;
    }

    public void setPlanetaryShield(boolean isPlanetaryShield) {
        planetaryShield = isPlanetaryShield;
    }
    public boolean getPlanetaryShield() {
        return planetaryShield;
    }
}

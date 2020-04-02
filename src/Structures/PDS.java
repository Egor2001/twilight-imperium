package Structures;

public class PDS extends Structure {
    private int[] SpaceCannon_ = new int[2];
    private boolean PlanetaryShield_;

    public int getNumberOfDicesForSpaceCannon() {
        return SpaceCannon_[1];
    }
    public boolean canHitFromSpaceCannon(int DiceValue) {
        return DiceValue >= SpaceCannon_[0];
    }
    public void setSpaceCannon(int DiceValue, int NumDices) {
        SpaceCannon_[0] = DiceValue;
        SpaceCannon_[1] = NumDices;
    }

    public void setPlanetaryShield(boolean isPlanetaryShield) {
        PlanetaryShield_ = isPlanetaryShield;
    }
    public boolean getPlanetaryShield() {
        return PlanetaryShield_;
    }
}

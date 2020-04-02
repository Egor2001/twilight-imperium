package com.twilight;

public class Ship implements Unit, Updatable {
    private int MoveValue_;
    private int CapacityValue_;
    private int CombatValue_;
    private int Cost_;

    private boolean CanDamaged_ = false;
    private boolean Damaged_;

    private int[] SpaceCannon_ = new int[2];
    private int[] Bombardment_ = new int[2];
    private int[] AntiFighterBarrage_ = new int[2];

    public int getMoveValue() {
        return MoveValue_;
    }
    public int getCapacityValue() {
        return CapacityValue_;
    }
    public int getCost() {
        return Cost_;
    }

    public boolean canHit(int DiceValue) {
        return DiceValue >= CombatValue_;
    }

    public boolean isDamaged() {
        return Damaged_;
    }
    public boolean canDamaged() {
        return CanDamaged_;
    }
    public void takeDamaged() {
        Damaged_ = true;
    }

    public int getNumberOfDicesForSpaceCannon() {
        return SpaceCannon_[1];
    }
    public boolean canHitFromSpaceCannon(int DiceValue) {
        return DiceValue >= SpaceCannon_[0];
    }

    public int getNumberOfDicesForBombardment() {
        return Bombardment_[1];
    }
    public boolean canHitFromBombardment(int DiceValue) {
        return DiceValue >= Bombardment_[0];
    }

    public int getNumberOfDicesForAntiFighterBarrage() {
        return AntiFighterBarrage_[1];
    }
    public boolean canHitFromAntiFighterBarrage(int DiceValue) {
        return DiceValue >= AntiFighterBarrage_[0];
    }

    @Override
    public void Update() {}
    @Override
    public void Upgrade() {}

    public void setMoveValue(int MoveValue) {
        MoveValue_ = MoveValue;
    }
    public void setCapacityValue(int CapacityValue) {
        CapacityValue_ = CapacityValue;
    }
    public void setCombatValue(int CombatValue) {
        CombatValue_ = CombatValue;
    }
    public void setCost(int Cost) {
        Cost_ = Cost;
    }

    public void setCanDamaged(boolean CanDamaged) {
        CanDamaged_ = CanDamaged;
    }

    public void setSpaceCannon(int DiceValue, int NumDices) {
        SpaceCannon_[0] = DiceValue;
        SpaceCannon_[1] = NumDices;
    }
    public void setBombardment(int DiceValue, int NumDices) {
        Bombardment_[0] = DiceValue;
        Bombardment_[1] = NumDices;
    }
    public void setAntiFighterBarrage(int DiceValue, int NumDices) {
        AntiFighterBarrage_[0] = DiceValue;
        AntiFighterBarrage_[1] = NumDices;
    }
}

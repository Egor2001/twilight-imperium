package com.twilight;

public interface Builder {
    void reset();

    void setMoveValue(int MoveValue);
    void setCapacityValue(int CapacityValue);
    void setCombatValue(int CombatValue);
    void setCost(int Cost);

    void setCanDamaged(boolean CanDamaged);

    void setSpaceCannon(int DiceValue, int NumDices);
    void setBombardment(int DiceValue, int NumDices);
    void setAntiFighterBarrage(int DiceValue, int NumDices);
}

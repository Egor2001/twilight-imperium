package ArmyUnits;

import base.Updatable;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;

public interface Unit extends Updatable, LoaderFromJSON {
    int getCost();
    boolean canHit(int diceValue);

    void setCombatValue(int newCombatValue);
    void setCost(int newCost);
}

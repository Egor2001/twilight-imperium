package ArmyUnits.Structures;

import org.json.JSONObject;

public class SpaceDock implements Structure {
    private boolean blockaded = false;
    private int productValue;

    public boolean isBlockaded() {
        return blockaded;
    }
    public int getValue() {
        return productValue;
    }

    public void setBlockaded(boolean isBlockaded) {
        blockaded = isBlockaded;
    }
    public void setProductValue(int value) {
        productValue = value;
    }

    @Override
    public void setAllFromJSON(JSONObject object) {
        blockaded = (boolean) object.get("blockaded");
        productValue = (int) object.get("productValue");
    }

    @Override
    public void update() {

    }
}

package ArmyUnits.Structures;

import ArmyUnits.Unit;
import base.controller.HierarchyController;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Writer;

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
        writer.write("Product value: " + productValue + "\n");

        if (blockaded) {
            writer.write("Blockaded\n");
        }
    }
}

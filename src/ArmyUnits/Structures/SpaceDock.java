package ArmyUnits.Structures;

import ArmyUnits.Unit;
import Races.Race;
import base.controller.HierarchyController;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Writer;

public class SpaceDock extends Structure {
    private boolean blockaded = false;
    private int productValue;
    private boolean canFightSpace;

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
        canFightSpace = (boolean) object.get("canFightSpace");
    }

    @Override
    public boolean canFightInSpace() {
        return canFightSpace;
    }

    @Override
    public void update() {

    }

    public SpaceDock(Race race) {
        super(race);
    }

    public static class Target extends HierarchyController.GameObjectTarget {
        public Target() {
            super();
        }
        public Target(HierarchyController.GameObjectTarget next) {
            super(next);
        }
        public Target(int index) {
            super(index);
        }
        public Target(HierarchyController.GameObjectTarget next, int index) {
            super(next, index);
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

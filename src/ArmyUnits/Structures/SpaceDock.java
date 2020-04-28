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
        blockaded = object.getBoolean("blockaded");
        productValue = object.getInt("productValue");
        canFightSpace = object.getBoolean("canFightSpace");
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
    public String getInfo(String start) {
        String result = start + "Product value: " + productValue;

        if (blockaded) {
            result += ", but blockaded";
        }

        return result;
    }
}

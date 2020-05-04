package player.units.Structures;

import base.user.GameObjectTarget;
import player.Race;
import org.json.JSONObject;

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

        setCanSustainDamaged(false);
        setDamaged(false);
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

    public static class Target extends GameObjectTarget {
        public Target() {
            super();
        }
        public Target(GameObjectTarget next) {
            super(next);
        }
        public Target(int index) {
            super(index);
        }
        public Target(GameObjectTarget next, int index) {
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

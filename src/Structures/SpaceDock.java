package Structures;

public class SpaceDock extends Structure {
    private boolean blockaded;
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
}

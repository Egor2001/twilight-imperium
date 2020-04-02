package Structures;

public class SpaceDock extends Structure {
    private boolean Blockaded_;
    private int ProductValue_;

    public boolean isBlockaded() {
        return Blockaded_;
    }
    public int getValue() {
        return ProductValue_;
    }

    public void setBlockaded(boolean isBlockaded) {
        Blockaded_ = isBlockaded;
    }
    public void setProductValue(int value) {
        ProductValue_ = value;
    }
}

package player;

public class Resources {
    private int numProducts;
    private int numGoods;

    private int numTacticPool;
    private int numFleetPool;
    private int numStrategyPool;

    public Resources() {
        numProducts = 0;
        numGoods = 0;
        numTacticPool = 0;
        numFleetPool = 0;
        numStrategyPool = 0;
    }

    public int getNumProducts() {
        return numProducts;
    }
    public int getNumGoods() {
        return numGoods;
    }
    public int getNumTacticPool() {
        return numTacticPool;
    }
    public int getNumFleetPool() {
        return numFleetPool;
    }
    public int getNumStrategyPool() {
        return numStrategyPool;
    }

    public void addNumProducts(int add) {
        numProducts += add;
    }
    public void addNumGoods(int add) {
        numGoods += add;
    }
    public void addNumTacticPool(int add) {
        numTacticPool += add;
    }
    public void addNumFleetPool(int add) {
        numFleetPool += add;
    }
    public void addNumStrategyPool(int add) {
        numStrategyPool += add;
    }

    public void setNumProducts(int value) {
        numProducts = value;
    }
    public void setNumGoods(int value) {
        numGoods = value;
    }
    public void setNumTacticPool(int value) {
        numTacticPool = value;
    }
    public void setNumFleetPool(int value) {
        numFleetPool = value;
    }
    public void setNumStrategyPool(int value) {
        numStrategyPool = value;
    }
}

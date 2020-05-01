package base.user;

public abstract class GameObjectTarget {

    private GameObjectTarget next;
    int index;

    public GameObjectTarget() {
        this.next = null;
        this.index = 0;
    }
    public GameObjectTarget(GameObjectTarget next) {
        this.next = next;
        this.index = 0;
    }

    public GameObjectTarget(int index) {
        this.next = null;
        this.index = index;
    }
    public GameObjectTarget(GameObjectTarget next, int index) {
        this.next = next;
        this.index = index;
    }

    public GameObjectTarget getNext() {
        return next;
    }

    public int getIndex() {
        return index;
    }
}

package base.view;

public class MessageString implements Viewable {
    String string;

    public MessageString(String string) {
        this.string = string;
    }

    @Override
    public String toString(String s) {
        return string;
    }

    @Override
    public String toString() {
        return toString("");
    }
}

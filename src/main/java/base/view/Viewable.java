package base.view;

import java.io.IOException;
import java.io.Writer;

public interface Viewable {
    void display(Writer writer) throws IOException;

    String toString();
    String toString(String s);
}

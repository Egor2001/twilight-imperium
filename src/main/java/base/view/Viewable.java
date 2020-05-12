package base.view;

import java.io.IOException;
import java.io.Writer;

public interface Viewable {
    default void display(Writer writer) throws IOException {
        writer.write(toString());
    }

    String toString();
    String toString(String s);
}

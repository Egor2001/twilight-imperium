package board;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;

public interface PlanetLoaderJSON {
    void setAllFromJSON(JSONObject object);
    default void setAllFromJSONFile(String raceName) {
        String[] className = getClass().getName().split("\\.");
        String filename = className[className.length - 1] + raceName + ".json";

        try (FileReader reader = new FileReader("etc/baseUnits/" + filename)) {
            JSONTokener token = new JSONTokener(reader);
            JSONObject object = new JSONObject(token);
            setAllFromJSON(object);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package board;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public interface PlanetLoaderJSON {
    void setAllFromJSON(JSONObject object);
    default void setAllFromJSONFile(String raceName) {
        String[] className = getClass().getName().split("\\.");
        String filename = className[className.length - 1] + raceName + ".json";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(
                "/etc/baseUnits/" + filename)))) {
            JSONTokener token = new JSONTokener(reader);
            JSONObject object = new JSONObject(token);
            setAllFromJSON(object);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

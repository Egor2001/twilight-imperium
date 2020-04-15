package tile;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;

class Planet extends TileObject {
    public Planet(String planet_name) {
        try (FileReader reader = new FileReader("basePlanets/" + planet_name + ".json")) {
            JSONTokener token = new JSONTokener(reader);
            JSONObject object = new JSONObject(token);

            Name = (String)object.get("name");
            Type = (String)object.get("type");
            Resource = (int)object.get("resource");
            Influence = (int)object.get("influence");
            Tech = (String)object.get("tech");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    String Name;
    String Type;
    int Resource;
    int Influence;
    String Tech;

    public void print() {
        System.out.println(Name + " " + Type + " " + Resource + " " + Influence + " " + Tech);
    }
}

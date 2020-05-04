package base.controller.invasion;

import board.Planet;
import player.Player;
import player.units.Unit;
import sun.jvmstat.monitor.Units;

import java.util.ArrayList;

public class BombardmentManager {
    private ArrayList<Unit> units;
    private ArrayList<Planet> planets;

    public BombardmentManager() {
        units = new ArrayList<>();
        planets = new ArrayList<>();
    }

    public void Add(ArrayList<Unit> units, Planet planet) {
        for (Unit unit: units) {
            int index = this.units.indexOf(unit);

            if (index == -1) {
                this.units.add(unit);
                this.planets.add(planet);
            }
            else {
                this.planets.set(index, planet);
            }
        }
    }
}

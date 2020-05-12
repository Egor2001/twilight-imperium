package base.controller.invasion;

import board.Planet;
import player.Player;
import player.units.Unit;

import java.util.ArrayList;

public class BombardmentManager {
    private ArrayList<Unit> units;
    private ArrayList<Planet> planets;
    private ArrayList<Integer> numHits;

    public BombardmentManager() {
        units = new ArrayList<>();
        planets = new ArrayList<>();
        numHits = new ArrayList<>();
    }

    public void Add(ArrayList<Unit> units, Planet planet) {
        for (Unit unit: units) {
            int index = this.units.indexOf(unit);

            if (index == -1) {
                this.units.add(unit);
                this.planets.add(planet);
                this.numHits.add(0);
            }
            else {
                this.planets.set(index, planet);
            }
        }
    }

    public ArrayList<Unit> getUnitsList() {
        return units;
    }
    public ArrayList<Planet> getPlanetsList() {
        ArrayList<Planet> planetList = new ArrayList<>();
        for (Planet planet: planets) {
            if (!planetList.contains(planet)) {
                planetList.add(planet);
            }
        }
        return planetList;
    }
    public Integer getHitOnPlanet(Planet planet) {
        Integer sumHits = 0;

        for (int i = 0; i < planets.size(); ++i) {
            if (planets.get(i) == planet) {
                sumHits += numHits.get(i);
            }
        }

        return sumHits;
    }
    public void setHitFromUnit(Unit unit, Integer hits) {
        int index = units.indexOf(unit);
        numHits.set(index, hits);
    }

    public ArrayList<Unit> getUnitsOnPlanet(Planet planet) {
        ArrayList<Unit> result = new ArrayList<>();

        for (int i = 0; i < units.size(); ++i) {
            if (planets.get(i) == planet) {
                result.add(units.get(i));
            }
        }

        return result;
    }
}

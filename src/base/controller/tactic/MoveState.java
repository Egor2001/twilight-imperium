package base.controller.tactic;

import base.user.GameObjectTarget;
import base.user.UserAcceptable;
import base.view.Viewable;
import board.TileObject;
import player.units.Unit;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class MoveState {
    private TileObject activeTile;
    private ArrayList<Unit> unitList;
    private ArrayList<ArrayList<Unit>> internalUnits;
    private ArrayList<ArrayList<TileObject>> ways;
    private MoveController moveController;

    public MoveState(MoveController moveController) {
        activeTile = null;
        unitList = new ArrayList<>();
        internalUnits = new ArrayList<>();
        ways = new ArrayList<>();
        this.moveController = moveController;
    }

    public void clear() {
        activeTile = null;
        unitList.clear();
        internalUnits.clear();
        ways.clear();
    }

    public void delUnit(Unit unit) {
        int index = unitList.indexOf(unit);
        if (index > -1) {
            unitList.remove(index);
            internalUnits.remove(index);
            ways.remove(index);
        } else {
            throw new IllegalArgumentException("This unit for del not in list");
        }
    }

    public void setActiveTile(TileObject activeTile) {
        if (this.activeTile != null && unitList.size() > 0) {
            throw new IllegalArgumentException("Active tile already choose and list units isn't empty");
        }

        this.activeTile = activeTile;
    }

    public void addWay(ArrayList<Unit> moveUnit, ArrayList<TileObject> way) {
        if (way.get(way.size() - 1) != activeTile) {
            throw new IllegalArgumentException("Final way isn't tile target");
        }

        ArrayList<Unit> badUnits = new ArrayList<>();

        for (Unit unit: moveUnit) {
            int index = unitList.indexOf(unit);

            if (index > -1) {
                if (ways.get(index) != null) {
                    badUnits.add(unit);
                    continue;
                }
                ways.set(index, way);
            } else {
                unitList.add(unit);
                ways.add(way);
                internalUnits.add(null);
            }
        }

        if (!badUnits.isEmpty()) {
            throw new IllegalArgumentException("Way was already add");
        }
    }

    public void addUnitsInsideUnit(Unit unit, ArrayList<Unit> insideUnits) {
        int index = unitList.indexOf(unit);
        if (index == -1) {
            throw new IllegalArgumentException("This unit not in list");
        }
        internalUnits.set(index, insideUnits);
    }

    public void addUnit(ArrayList<Unit> moveUnits) {
        ArrayList<Unit> badUnits = new ArrayList<>();

        for (Unit unit: moveUnits) {
            if (unitList.contains(unit)) {
                badUnits.add(unit);
                continue;
            }

            unitList.add(unit);
            ways.add(null);
            internalUnits.add(null);
        }

        if (!badUnits.isEmpty()) {
            throw new IllegalArgumentException("Unit was already add");
        }
    }

    public TileObject getActiveTile() {
        return activeTile;
    }
    public ArrayList<Unit> getUnitList() {
        return unitList;
    }
    public ArrayList<Unit> getInternalUnits(Unit unit) {
        return internalUnits.get(unitList.indexOf(unit));
    }
    public ArrayList<TileObject> getWay(Unit unit) {
        return ways.get(unitList.indexOf(unit));
    }

    public String toString(String start) {
        StringBuilder result = new StringBuilder(start + "Move State now:\n");
        result.append(start).append("Active Tile:\n");
        result.append(activeTile.GetTile().getView(null).toString(start + "  ")).append("\n");

        if (unitList.size() > 0) {
            result.append(start).append("Move units:\n");
            for(Unit unit: unitList) {
                result.append(getUnitInfo(unit, start + "  "));
            }
        }

        return result.toString();
    }
    public String toString() {
        return toString("");
    }

    public String getUnitInfo(Unit unit, String start) {
        StringBuilder result = new StringBuilder(unit.getView(null).toString(start) + ":\n");

        ArrayList<Unit> internalUnit = getInternalUnits(unit);
        if (internalUnit.size() > 0) {
            result.append("  ").append(start).append("Internal units:\n");
            for (Unit unitInternal: internalUnit) {
                result.append(unitInternal.getView(null).toString(start + "    ")).append("\n");
            }
        }

        ArrayList<TileObject> way = getWay(unit);
        result.append("  ").append(start).append("Way's tiles:\n");
        result.append("    ").append(start).append("Tile: ").append(
                moveController.getGameState().getTileArmyManager().getTileObject(unit).GetTile().getId());
        for (TileObject tile: way) {
            result.append(" -> Tile: ").append(tile.GetTile().getId());
        }

        return result.toString();
    }
}

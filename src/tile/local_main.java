package tile;

import ArmyUnits.FactoryUnit;
import ArmyUnits.Ships.Flagship;
import ArmyUnits.Unit;
import Races.Arborec;
import Races.Race;
import base.controller.GameController;
import base.controller.HierarchyController;

import base.controller.phase.action.PlayerActionMove;
import base.model.Player;

import java.util.ArrayList;

public class local_main {
    public static void main(String[] args) throws Exception {
        GameController gm = GameController.getInstance();
        TileArmyController controller = gm.getGameState().getController();
        Board board = gm.getGameState().getBoard();
        ArrayList<Player> players = gm.getGameState().getPlayers();
        players.add(new Player("Igor", "Winnu"));

        controller.add(players.get(0).addUnit("Carrier"), board.tiles_.get(2).space_);
        controller.add(players.get(0).addUnit("Carrier"), board.tiles_.get(1).space_);
        controller.add(players.get(0).addUnit("Destroyer"), board.tiles_.get(1).space_);

        System.out.println(board.getView(null));

        HierarchyController.GameObjectTarget tr = HierarchyController.parseTarget("Army.Ship.0");
        ArrayList<HierarchyController.GameObjectTarget> list = new ArrayList<>();
        list.add(HierarchyController.parseTarget("Tile.1.Space"));

        new PlayerActionMove(tr, list).execute(gm.getGameState(), players.get(0));
        System.out.println(board.getView(null));

        HierarchyController.GameObjectTarget tr2 = HierarchyController.parseTarget("Army.Ship.2");
        System.out.println(((Unit) players.get(0).getObject(tr2)).getInfo());
    }
}
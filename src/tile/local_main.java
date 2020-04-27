package tile;

import base.controller.GameController;
import base.controller.HierarchyController;

import base.controller.phase.PlayerActionMove;
import base.model.Player;

import java.util.ArrayList;

public class local_main {
    public static void main(String[] args) throws Exception {
        GameController gm = GameController.getInstance();
        TileArmyController controller = gm.getGameState().getController();
        Board board = gm.getGameState().getBoard();
        ArrayList<Player> players = gm.getGameState().getPlayers();
        players.set(0, new Player("Igor", "Arborec"));

        controller.add(players.get(0).addUnit("Carrier"), board.tiles_.get(2).space_);
        controller.add(players.get(0).addUnit("Carrier"), board.tiles_.get(1).space_);
        controller.add(players.get(0).addUnit("Destroyer"), board.tiles_.get(1).space_);

        System.out.println(board.getView(null));

        HierarchyController.GameObjectTarget tr = HierarchyController.parseTarget("Army.Ship.1");
        ArrayList<HierarchyController.GameObjectTarget> list = new ArrayList<>();
        list.add(HierarchyController.parseTarget("Tile.0.Space"));

        gm.getGameState().handleCommand(players.get(0), new PlayerActionMove(tr, list));
        System.out.println(board.getView(null));
    }
}
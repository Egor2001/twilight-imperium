package Tests;

import ArmyUnits.Unit;
import base.controller.GameController;
import base.controller.HierarchyController;
import base.controller.PhaseController;
import base.model.Player;
import junit.framework.TestCase;
import tile.Board;
import tile.TileArmyController;

import java.util.ArrayList;

public class MoveTest extends TestCase {
    protected void setUp() throws Exception {

    }

    protected void tearDown() throws Exception {

    }

    public void testMove () {
        try {
            GameController gm = GameController.getInstance();
            TileArmyController controller = gm.getGameState().getController();
            Board board = gm.getGameState().getBoard();
            ArrayList<Player> players = gm.getGameState().getPlayers();
            players.add(new Player("Igor", "Arborec"));

            controller.add(players.get(0).addUnit("Carrier"), board.tiles_.get(2).space_);
            controller.add(players.get(0).addUnit("Carrier"), board.tiles_.get(1).space_);
            controller.add(players.get(0).addUnit("Destroyer"), board.tiles_.get(1).space_);

            //System.out.println(board.getView(null));

            HierarchyController.GameObjectTarget tr = HierarchyController.parseTarget("Army.Ship.0");
            ArrayList<HierarchyController.GameObjectTarget> list = new ArrayList<>();
            list.add(HierarchyController.parseTarget("Tile.1.Space"));

            gm.getGameState().handleActionCommand(players.get(0), new PhaseController.PlayerActionMove(tr, list));
            //System.out.println(board.getView(null));

            HierarchyController.GameObjectTarget tr2 = HierarchyController.parseTarget("Army.Ship.2");
            //System.out.println(((Unit) players.get(0).getObject(tr2)).getInfo());
        }
        catch (Exception e) {
            fail();
        }
    }
}

class SimpleMove extends TestCase {
    protected void setUp() throws Exception {

    }

    protected void tearDown() throws Exception {

    }

    public void testMove () {
        try {
            GameController gm = GameController.getInstance();
            TileArmyController controller = gm.getGameState().getController();
            Board board = gm.getGameState().getBoard();
            ArrayList<Player> players = gm.getGameState().getPlayers();
            players.add(new Player("Igor", "Arborec"));

            controller.add(players.get(0).addUnit("Carrier"), board.tiles_.get(2).space_);
            controller.add(players.get(0).addUnit("Carrier"), board.tiles_.get(1).space_);
            controller.add(players.get(0).addUnit("Destroyer"), board.tiles_.get(1).space_);

            //System.out.println(board.getView(null));

            HierarchyController.GameObjectTarget tr = HierarchyController.parseTarget("Army.Ship.0");
            ArrayList<HierarchyController.GameObjectTarget> list = new ArrayList<>();
            list.add(HierarchyController.parseTarget("Tile.1.Space"));

            gm.getGameState().handleActionCommand(players.get(0), new PhaseController.PlayerActionMove(tr, list));
            //System.out.println(board.getView(null));

            HierarchyController.GameObjectTarget tr2 = HierarchyController.parseTarget("Army.Ship.2");
            //System.out.println(((Unit) players.get(0).getObject(tr2)).getInfo());
        }
        catch (Exception e) {
            fail();
        }
    }
}
package base.controller.invasion;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.controller.CommandResponse;

import base.controller.combat.SpaceCombatController;
import base.controller.global.GlobalCommandController;
import base.controller.groundCombat.PlayerSpaceCannon;
import base.model.GameState;
import base.user.CommandRequestable;
import board.Tile;
import board.TileObject;
import player.Player;
import player.units.Unit;
import sun.jvm.hotspot.CommandProcessor;

import java.util.ArrayList;


public class InvasionController extends AbstractController {
    public enum CombatPhase {
        BOMBARDMENT("BOMBARDMENT"),
        ASSIGN_HITS("ASSIGN_HITS"),
        LANDING("LANDING");

        public final String name;

        CombatPhase(String name) {
            this.name = name;
        }

        public static InvasionController.CombatPhase next(InvasionController.CombatPhase phase) {
            switch (phase) {
                case BOMBARDMENT:
                    return ASSIGN_HITS;
                case ASSIGN_HITS:
                    return LANDING;
                case LANDING:
                    return null;
            }

            return null;
        }
    }

    private Player invader;
    private Player defender;
    private Tile tile;
    private GameState gameState;
    private CombatPhase combatPhase;

    private BombardmentManager bombardmentManager;

    public BombardmentManager GetBombardmentManager () {
        return bombardmentManager;
    }

    public InvasionController(CommandRequestable userInterface, GameState gameState,
                                 GlobalCommandController globalCommandController, Tile tile, Player player) {
        super(userInterface, globalCommandController);
        super.putCommand("bombard", new PlayerInvasionBombard(this));

        super.putCommand("make-rolls", new PlayerInvasionMakeRoll(this));
        super.putCommand("assign-hits", new PlayerInvasionAssignHits(this));

        super.putCommand("land-troops", new PlayerInvasionLandTroops(this));
        super.putCommand("finish", new PlayerInvasionFinish(this));

        this.invader = player;
        this.defender = null;
        for (TileObject tileObject: tile.GetPlanets()) {
            ArrayList<Unit> defenderUnits = gameState.getTileArmyManager().getUnit(tileObject);
            if (defenderUnits.size() > 0) {
                defender = Player.getRacePlayerManager().getPlayer(defenderUnits.get(0).getRace());
                break;
            }
        }
        this.tile = tile;
        this.gameState = gameState;
        this.bombardmentManager = new BombardmentManager();
    }

    void DoBombardment() {
        CommandResponse response;

        requestCommand(invader, "Insert planet to bombard");
    }
    CombatPhase getCombatPhase() {
        return combatPhase;
    }

    @Override
    public CommandResponse start() {
        combatPhase = CombatPhase.BOMBARDMENT;
        AbstractCommand command = null;
        CommandResponse response = CommandResponse.DECLINED;

        while (response != CommandResponse.END_EVENT) {
            command = requestCommand(invader, "bombard");
            response = command.execute(invader);
        }
        combatPhase = CombatPhase.next(combatPhase);

        while (response != CommandResponse.ACCEPTED && defender != null) {
            command = requestCommand(defender, "assign hits");
            response = command.execute(defender);
        }
        combatPhase = CombatPhase.next(combatPhase);

        bombardmentManager = new BombardmentManager();
        while (response != CommandResponse.END_EVENT) {
            command = requestCommand(invader, "land troops");
            response = command.execute(invader);
        }


        return CommandResponse.ACCEPTED;
    }
    @Override
    public GameState getGameState() {
        return null;
    }
    @Override
    public AbstractCommand getExitCommand() {
        return null;
    }
}

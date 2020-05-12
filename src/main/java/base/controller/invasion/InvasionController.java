package base.controller.invasion;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.controller.CommandResponse;

import base.controller.global.GlobalCommandController;
import base.controller.groundCombat.PlayerSpaceCannon;
import base.model.GameState;
import base.user.CommandRequestable;
import board.Tile;
import player.Player;


public class InvasionController extends AbstractController {
    public enum CombatPhase {
        FINISH(null),
        LANDING(InvasionController.CombatPhase.FINISH),
        ASSIGN_HITS(InvasionController.CombatPhase.LANDING),
        BOMBARDMENT(InvasionController.CombatPhase.ASSIGN_HITS);

        CombatPhase(InvasionController.CombatPhase next) {
            this.next = next;
        }

        public InvasionController.CombatPhase next;
    }

    private Player invader;
    private Tile tile;
    private GameState gameState;
    private CombatPhase combatPhase;

    private BombardmentManager bombardmentManager;

    public BombardmentManager GetBombardmentManager () {
        return bombardmentManager;
    }

    protected InvasionController(CommandRequestable userInterface, GameState gameState,
                                 GlobalCommandController globalCommandController, Tile tile, Player player) {
        super(userInterface, globalCommandController);
        super.putCommand("bombard", new PlayerInvasionBombard(this));

        super.putCommand("make-rolls", new PlayerInvasionMakeRoll(this));
        super.putCommand("assign-hits", new PlayerInvasionAssignHits(this));

        super.putCommand("land-troops", new PlayerInvasionLandTroops(this));
        super.putCommand("finish", new PlayerInvasionFinish(this));

        this.invader = player;
        this.tile = tile;
        this.gameState = gameState;
        this.bombardmentManager = new BombardmentManager();
    }

    void DoBombardment() {
        CommandResponse response;

        requestCommand(invader, "Insert planet to bombard");
    }

    @Override
    public CommandResponse start() {
        AbstractCommand command = new PlayerInvasionBombard(this);
        CommandResponse response = CommandResponse.DECLINED;


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

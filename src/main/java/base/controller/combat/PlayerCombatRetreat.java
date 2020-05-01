package base.controller.combat;

import base.controller.CommandResponse;
import base.model.GameState;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import board.Board;
import board.TileObject;
import player.Player;

public class PlayerCombatRetreat extends PlayerCombatCommand {

    private GameObjectTarget retreatTileTarget;

    public PlayerCombatRetreat(SpaceCombatController controller) {
        super(controller);
        this.retreatTileTarget = null;
    }

    public PlayerCombatRetreat(SpaceCombatController controller, GameObjectTarget retreatTileTarget) {
        this(controller);
        this.retreatTileTarget = retreatTileTarget;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        GameObjectTarget retreatTileTarget = userInterface.requestTarget("retreat tile");
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        SpaceCombatController.CombatPhase phase = ((SpaceCombatController) getController()).getCombatPhase();
        if (phase != SpaceCombatController.CombatPhase.COMBAT_RETREAT) {
            getController().getUserInterface().reportError("can't announce retreat in " + phase.toString() + " phase");
            return CommandResponse.DECLINED;
        }

        TileObject retreatTile = null;
        try {
            GameState gameState = getController().getGameState();
            retreatTile = (TileObject) gameState.getBoard().getObject(retreatTileTarget);
        }
        catch (Exception exception) {
            getController().getUserInterface().reportError("invalid target: " + exception.getMessage());
            return CommandResponse.DECLINED;
        }

        boolean canRetreat = ((SpaceCombatController) getController()).announceRetreat(player, retreatTile);
        if (!canRetreat) {
            getController().getUserInterface().reportError("somebody retreated before");
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

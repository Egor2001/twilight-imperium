package base.controller.phase.action;

import base.controller.CommandResponse;
import base.model.GameState;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import board.TileObject;
import player.Player;

public class PlayerActionAddSpaceDock extends PlayerActionCommand {

    public GameObjectTarget planetTarget = null;

    public PlayerActionAddSpaceDock(ActionPhaseController controller) {
        super(controller);
        this.planetTarget = null;
    }

    public PlayerActionAddSpaceDock(ActionPhaseController controller,
                                    String unitName, GameObjectTarget tileObjectTarget) {
        this(controller);
        this.planetTarget = tileObjectTarget;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        planetTarget = userInterface.requestTarget("planet");
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        GameState gameState = ((ActionPhaseController) getController()).getGameState();

        try {
            TileObject tileObject = (TileObject) gameState.getBoard().getObject(planetTarget);
            gameState.getTileArmyManager().add(player.getRace().createSpaceDock(), tileObject);
        }
        catch (Exception exception) {
            getController().getUserInterface().reportError(exception.getMessage());
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

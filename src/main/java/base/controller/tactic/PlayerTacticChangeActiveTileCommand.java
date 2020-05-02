package base.controller.tactic;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import base.view.MessageString;
import board.Tile;
import board.TileObject;
import player.Player;
import player.units.Unit;

public class PlayerTacticChangeActiveTileCommand extends PlayerTacticCommand {
    private GameObjectTarget tileObjectTarget;

    PlayerTacticChangeActiveTileCommand(MoveController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        controller.getUserInterface().displayView(new MessageString("processing TACTIC command: CHANGE_ACTIVE_TILE"));
        tileObjectTarget = userInterface.requestTarget("new active tile");
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        TileObject tileObject = (TileObject) player.getObject(tileObjectTarget);

        try {
            ((MoveController) controller).getMoveState().setActiveTile(tileObject);
        } catch (Exception exception) {
            controller.getUserInterface().displayView(new MessageString(exception.getMessage()));
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

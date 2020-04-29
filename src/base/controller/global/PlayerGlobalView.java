package base.controller.global;

import base.user.*;
import base.controller.CommandResponse;
import base.controller.PlayerCommand;
import base.model.GameState;
import base.view.Viewable;
import player.Player;

public class PlayerGlobalView implements PlayerCommand {

    private GameObjectTarget target;

    public PlayerGlobalView() {
        this.target = null;
    }

    public PlayerGlobalView(GameObjectTarget target) {
        this.target = target;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        target = userInterface.requestTarget("view");
        return true;
    }

    @Override
    public CommandResponse execute(GameState gameState, Player player) {
        try {
            UserAcceptable gameObject =
                    (UserAcceptable) gameState.getObject(target);

            Viewable view = gameObject.getView(null);
            System.out.println(view.toString());
        }
        catch (IllegalArgumentException exception) {
            return CommandResponse.DECLINED;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

        return CommandResponse.ACCEPTED;
    }
}

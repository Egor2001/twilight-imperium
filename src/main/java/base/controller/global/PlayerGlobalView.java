package base.controller.global;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.user.*;
import base.controller.CommandResponse;
import base.model.GameState;
import base.view.Viewable;
import player.Player;

public class PlayerGlobalView extends AbstractCommand {

    private GameObjectTarget target;

    public PlayerGlobalView(AbstractController controller) {
        super(controller);
        this.target = null;
    }

    public PlayerGlobalView(AbstractController controller, GameObjectTarget target) {
        this(controller);
        this.target = target;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        target = userInterface.requestTarget("view");
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        GameState gameState = ((GlobalCommandController) getController()).getGameState();

        try {
            UserAcceptable gameObject =
                    (UserAcceptable) gameState.getObject(target);

            Viewable view = gameObject.getView(null);
            getController().getUserInterface().displayView(view);
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

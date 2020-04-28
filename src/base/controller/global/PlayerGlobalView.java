package base.controller.global;

import base.controller.CommandRequestable;
import base.controller.CommandResponse;
import base.controller.HierarchyController;
import base.controller.PlayerCommand;
import base.model.GameState;
import base.model.Player;

public class PlayerGlobalView implements PlayerCommand {

    private HierarchyController.GameObjectTarget target;

    public PlayerGlobalView() {
        this.target = null;
    }

    public PlayerGlobalView(HierarchyController.GameObjectTarget target) {
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
            HierarchyController.UserAcceptable gameObject =
                    (HierarchyController.UserAcceptable) gameState.getBoard().getObject(target);

            HierarchyController.Viewable view = gameObject.getView(null);
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

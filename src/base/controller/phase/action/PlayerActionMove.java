package base.controller.phase.action;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.controller.CommandResponse;
import base.user.CommandRequestable;
import base.model.GameState;
import player.Player;

public class PlayerActionMove extends PlayerActionCommand {

    public PlayerActionMove(AbstractController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {


        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        System.out.println("processing ACTION command: MOVE");

        //MoveController moveController = new MoveController();

        return CommandResponse.ACCEPTED;
    }
}

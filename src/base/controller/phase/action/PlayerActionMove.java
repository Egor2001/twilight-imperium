package base.controller.phase.action;

import base.controller.AbstractController;
import base.controller.CommandResponse;
import base.controller.fight.SpaceCombatController;
import base.controller.tactic.MoveController;
import base.controller.tactic.MoveState;
import base.user.CommandRequestable;
import board.TileObject;
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

        MoveController moveController = new MoveController(controller.getUserInterface(), controller.getGameState(),
                                                           controller.getGlobalCommandController(), player);

        boolean result = moveController.start();
        if (!result) {
            return CommandResponse.DECLINED;
        }

        MoveState moveState = moveController.getMoveState();
        TileObject activeTile = moveState.getActiveTile();
        SpaceCombatController combatController =
                new SpaceCombatController(controller.getUserInterface(), controller.getGameState(),
                                          controller.getGlobalCommandController(), activeTile, player);

        result = combatController.start();
        if (!result) {
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

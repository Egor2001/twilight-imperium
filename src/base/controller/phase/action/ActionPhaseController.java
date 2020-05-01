package base.controller.phase.action;

import base.controller.AbstractController;
import base.user.CommandRequestable;
import base.controller.CommandResponse;
import base.controller.global.GlobalCommandController;
import base.model.GameState;
import player.Player;

import java.util.ArrayList;

public class ActionPhaseController extends AbstractController {

    private GameState gameState;

    public ActionPhaseController(CommandRequestable userInterface, GameState gameState,
                                 GlobalCommandController globalCommandController) {
        super(userInterface, globalCommandController);
        this.gameState = gameState;

        super.putCommand("add-unit", new PlayerActionAddUnit(this));
        super.putCommand("move", new PlayerActionMove(this));
        super.putCommand("pass", new PlayerActionPass(this));
    }

    @Override
    public boolean start() {
        ArrayList<Player> players = gameState.getPlayers();
        CommandResponse response = CommandResponse.DECLINED;

        PlayerActionCommand playerAction = null;
        boolean[] isPassed = new boolean[players.size()];
        int numPassed = 0;
        while (numPassed != players.size()) {
            for (int idx = 0; idx != players.size(); ++idx) {
                if (!isPassed[idx]) {
                    playerAction = (PlayerActionCommand) requestCommand(players.get(idx), "action");
                    response = playerAction.execute(players.get(idx));

                    if (response == CommandResponse.DECLINED) {
                        --idx;
                    }
                    if (response == CommandResponse.END_EVENT) {
                        isPassed[idx] = true;
                        ++numPassed;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }
}

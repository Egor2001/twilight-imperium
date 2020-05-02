package base.controller.phase.action;

import base.controller.AbstractCommand;
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
    public CommandResponse start() {
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
                    else if (response == CommandResponse.END_EVENT) {
                        isPassed[idx] = true;
                        ++numPassed;
                    }
                    else if (response == CommandResponse.END_GAME) {
                        return response;
                    }
                }
            }
        }

        return CommandResponse.ACCEPTED;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public AbstractCommand getExitCommand() {
        return new PlayerActionCommand(this) {
            @Override
            public boolean inputCommand(CommandRequestable userInterface) {
                return false;
            }

            @Override
            public CommandResponse execute(Player player) {
                return CommandResponse.END_GAME;
            }
        };
    }
}

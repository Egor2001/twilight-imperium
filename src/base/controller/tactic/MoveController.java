package base.controller.tactic;

import base.controller.CommandController;
import base.controller.CommandResponse;
import base.controller.global.GlobalCommandController;
import base.controller.phase.action.PlayerActionAddUnit;
import base.controller.phase.action.PlayerActionCommand;
import base.controller.phase.action.PlayerActionMove;
import base.model.GameState;
import base.user.CommandRequestable;
import player.Player;

import java.util.ArrayList;

public class MoveController extends CommandController {
    private GameState gameState;

    public MoveController(CommandRequestable userInterface, GameState gameState,
                          GlobalCommandController globalCommandController) {
        super(userInterface, globalCommandController);
        this.gameState = gameState;

        super.putCommand("add-unit-to-move", new PlayerTacticAddUnitToMoveCommand());
        super.putCommand("add-internal", new PlayerTacticAddInternalCommand());
        super.putCommand("add-way", new PlayerTacticAddWayCommand());
        super.putCommand("move", new PlayerTacticMoveCommand());
    }

    @Override
    public void start() {
        ArrayList<Player> players = gameState.getPlayers();

        for (Player player: players) {
            MoveState moveState = new MoveState();
            CommandResponse response = CommandResponse.DECLINED;

            PlayerTacticCommand playerTacticCommand = new PlayerTacticSetActiveTileCommand(moveState);
            playerTacticCommand.inputCommand(userInterface);
            playerTacticCommand.execute(gameState, player);

            while(response != CommandResponse.END_EVENT) {
                playerTacticCommand = (PlayerTacticCommand) requestCommand(player, "move");
                playerTacticCommand.setMoveState(moveState);
                response = playerTacticCommand.execute(gameState, player);
            }
        }
    }
}

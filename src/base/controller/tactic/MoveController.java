package base.controller.tactic;

import base.controller.AbstractController;
import base.controller.CommandResponse;
import base.controller.global.GlobalCommandController;
import base.model.GameState;
import base.user.CommandRequestable;
import player.Player;

public class MoveController extends AbstractController {
    private GameState gameState;
    private Player player;

    public MoveController(CommandRequestable userInterface, GameState gameState,
                          GlobalCommandController globalCommandController, Player player) {
        super(userInterface, globalCommandController);
        this.gameState = gameState;
        this.player = player;

        super.putCommand("add-unit-to-move", new PlayerTacticAddUnitToMoveCommand(this));
        super.putCommand("add-internal", new PlayerTacticAddInternalCommand(this));
        super.putCommand("add-way", new PlayerTacticAddWayCommand(this));
        super.putCommand("end-move", new PlayerTacticMoveCommand(this));
    }

    @Override
    public void start() {
        MoveState moveState = new MoveState();
        CommandResponse response = CommandResponse.DECLINED;

        PlayerTacticCommand playerTacticCommand = new PlayerTacticSetActiveTileCommand(this, moveState);
        playerTacticCommand.inputCommand(userInterface);
        playerTacticCommand.execute(player);

        while(response != CommandResponse.END_EVENT) {
            playerTacticCommand = (PlayerTacticCommand) requestCommand(player, "move");
            playerTacticCommand.setMoveState(moveState);
            response = playerTacticCommand.execute(player);
        }
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }
}

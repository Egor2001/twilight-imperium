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
    private MoveState moveState;

    public MoveController(CommandRequestable userInterface, GameState gameState,
                          GlobalCommandController globalCommandController, Player player) {
        super(userInterface, globalCommandController);
        this.gameState = gameState;
        this.player = player;
        this.moveState = null;

        super.putCommand("add-unit", new PlayerTacticAddUnitToMoveCommand(this));
        super.putCommand("del-unit", new PlayerTacticDelUnitCommand(this));
        super.putCommand("add-internal", new PlayerTacticAddInternalCommand(this));
        super.putCommand("add-way", new PlayerTacticAddWayCommand(this));
        super.putCommand("break", new PlayerTacticBreakCommand(this));
        super.putCommand("clear", new PlayerTacticClearCommand(this));
        super.putCommand("end-move", new PlayerTacticEndMoveCommand(this));
        super.putCommand("view-all-moves", new PlayerTacticViewMovesCommand(this));
        super.putCommand("view-unit-move", new PlayerTacticViewUnitCommand(this));
    }

    @Override
    public boolean start() {
        moveState = new MoveState();
        CommandResponse response = CommandResponse.DECLINED;
        boolean error = false;

        PlayerTacticCommand playerTacticCommand = new PlayerTacticSetActiveTileCommand(this, moveState);
        playerTacticCommand.inputCommand(userInterface);
        playerTacticCommand.execute(player);

        while(response != CommandResponse.END_EVENT) {
            playerTacticCommand = (PlayerTacticCommand) requestCommand(player, "move");

            response = playerTacticCommand.execute(player);
            if (response == CommandResponse.DECLINED) {
                error = true;
            }
            if (response == CommandResponse.BREAK) {
                error = true;
                break;
            }
        }

        return !error;
    }

    public MoveState getMoveState() {
        return moveState;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }
}

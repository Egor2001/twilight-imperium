package base.controller.tactic;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.controller.CommandResponse;
import base.controller.global.GlobalCommandController;
import base.controller.global.PlayerGlobalExit;
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

        super.putCommand("add-unit-to-move", new PlayerTacticAddUnitToMoveCommand(this));
        super.putCommand("add-internal", new PlayerTacticAddInternalCommand(this));
        super.putCommand("add-way", new PlayerTacticAddWayCommand(this));
        super.putCommand("break", new PlayerTacticBreakCommand(this));
        super.putCommand("clear", new PlayerTacticClearCommand(this));
        super.putCommand("del-unit", new PlayerTacticDelUnitCommand(this));
        super.putCommand("end-move", new PlayerTacticEndMoveCommand(this));
    }

    @Override
    public CommandResponse start() {
        moveState = new MoveState();
        CommandResponse response = CommandResponse.DECLINED;
        boolean error = false;

        PlayerTacticCommand playerTacticCommand = new PlayerTacticSetActiveTileCommand(this, moveState);
        playerTacticCommand.inputCommand(userInterface);
        playerTacticCommand.execute(player);

        while(response != CommandResponse.END_EVENT) {
            playerTacticCommand = (PlayerTacticCommand) requestCommand(player, "move");
            playerTacticCommand.setMoveState(moveState);

            response = playerTacticCommand.execute(player);
            if (response == CommandResponse.DECLINED) {
                error = true;
            }
            else if (response == CommandResponse.BREAK) {
                error = true;
                break;
            }
            else if (response == CommandResponse.END_GAME) {
                return response;
            }
        }

        return (error ? CommandResponse.DECLINED : CommandResponse.ACCEPTED);
    }

    public MoveState getMoveState() {
        return moveState;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public AbstractCommand getExitCommand() {
        return new PlayerTacticCommand(this) {
            @Override
            public void setMoveState(MoveState moveState) {
            }

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

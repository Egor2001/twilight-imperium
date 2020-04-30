package base.controller.phase.action;

import base.controller.tactic.MoveController;
import base.user.GameObjectTarget;
import player.units.Ships.Ship;
import base.controller.CommandResponse;
import base.user.CommandRequestable;
import base.model.GameState;
import player.Player;
import board.Board;
import board.TileObject;

import java.util.ArrayList;

public class PlayerActionMove implements PlayerActionCommand {

    public PlayerActionMove() {}

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {


        return true;
    }

    @Override
    public CommandResponse execute(GameState gameState, Player player) {
        System.out.println("processing ACTION command: MOVE");

        //MoveController moveController = new MoveController();

        return CommandResponse.ACCEPTED;
    }
}

package base.controller;

import base.controller.CommandRequestable;
import base.model.GameState;
import base.model.Player;

public interface PlayerCommand {
    boolean inputCommand(CommandRequestable userInterface);
    boolean execute(GameState gameState, Player player);
}

package base.controller;

import base.model.GameState;
import base.model.Player;

public interface PlayerCommand {
    boolean inputCommand(CommandRequestable userInterface);
    CommandResponse execute(GameState gameState, Player player);
}

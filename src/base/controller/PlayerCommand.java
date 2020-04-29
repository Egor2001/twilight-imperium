package base.controller;

import base.model.GameState;
import base.user.CommandRequestable;
import player.Player;

public interface PlayerCommand {
    boolean inputCommand(CommandRequestable userInterface);
    CommandResponse execute(GameState gameState, Player player);
}

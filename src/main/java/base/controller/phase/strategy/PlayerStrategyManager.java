package base.controller.phase.strategy;

import base.controller.strategy.AbstractStrategy;
import base.model.GameState;
import player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerStrategyManager {

    private HashMap<Player, AbstractStrategy> playerStrategyMap;

    public PlayerStrategyManager(ArrayList<Player> players) {
        this.playerStrategyMap = new HashMap<>();
        for (Player player : players) {
            this.playerStrategyMap.put(player, null);
        }
    }

    public void pickNew(Player player, AbstractStrategy strategy) {
        playerStrategyMap.remove(player);
        playerStrategyMap.put(player, strategy);
    }

    public AbstractStrategy get(Player player) {
        return playerStrategyMap.get(player);
    }
}

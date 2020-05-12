package base.controller.strategy;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.controller.CommandResponse;
import base.model.GameState;
import base.user.CommandRequestable;
import player.Player;

public abstract class AbstractStrategy extends AbstractController {

    private GameState gameState;
    private Player owner;
    private int priority;

    protected AbstractStrategy(CommandRequestable userInterface, GameState gameState,
                               Player owner, int priority) {
        super(userInterface);
        super.putCommand("pass", new StrategyPass(this));
        this.gameState = gameState;
        this.owner = owner;
        this.priority = priority;
    }

    public Player getOwner() {
        return owner;
    }

    public abstract CommandResponse ownerAction();

    @Override
    public CommandResponse start() {
        return ownerAction();
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public abstract AbstractCommand getExitCommand();
}

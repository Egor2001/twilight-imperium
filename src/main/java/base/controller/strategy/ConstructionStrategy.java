package base.controller.strategy;

import base.controller.AbstractCommand;
import base.controller.CommandResponse;
import base.controller.phase.strategy.StrategyPhaseController;
import base.model.GameState;
import base.user.CommandRequestable;
import board.Planet;
import board.Space;
import board.TileArmyManager;
import player.Player;
import player.units.Structures.SpaceDock;
import player.units.Unit;

import java.util.ArrayList;

public class ConstructionStrategy extends AbstractStrategy {

    private TileArmyManager tileArmyManager;

    public ConstructionStrategy(StrategyPhaseController strategyPhaseController,
                                Player owner, int priority) {
        super(strategyPhaseController, owner, priority);
        super.putCommand("construct", new ConstructionStrategyConstruct(this));
        this.tileArmyManager = getGameState().getTileArmyManager();
    }

    public boolean construct(Player player, Planet planet, ArrayList<String> unitNames) {
        Space space = planet.GetTile().GetSpace();

        int productValue = 1;
        boolean canConstruct = false;
        for (Unit unit : planet.GetObjectUnits()) {
            if (unit instanceof SpaceDock && unit.getRace() == player.getRace()) {
                canConstruct = true;
                productValue += ((SpaceDock) unit).getValue();
            }
        }

        if (!canConstruct) {
            userInterface.reportError("expect space dock in planet");
            return false;
        }
        else if (unitNames.size() > productValue) {
            userInterface.reportError("too many units");
            return false;
        }

        for (String unitName : unitNames) {
            Unit unit = null;
            try {
                unit = player.addUnit(unitName);
            }
            catch (IllegalArgumentException exception) {
                userInterface.reportError("can't create unit: " + exception.getMessage());
                return false;
            }

            tileArmyManager.add(unit, space);
        }

        return true;
    }

    @Override
    public CommandResponse ownerAction() {
        ConstructionStrategyConstruct command = new ConstructionStrategyConstruct(this);
        command.inputCommand(getUserInterface());

        return command.execute(getOwner());
    }

    @Override
    public CommandResponse start() {
        CommandResponse response = super.start();
        if (response != CommandResponse.ACCEPTED) {
            return response;
        }

        Player owner = getOwner();
        ArrayList<Player> players = getGameState().getPlayers();
        for (Player player : players) {
            if (player == owner) {
                continue;
            }

            AbstractStrategyCommand command = (AbstractStrategyCommand) requestCommand(player, "construction");

            while (response != CommandResponse.ACCEPTED) {
                response = command.execute(player);

                if (response == CommandResponse.END_GAME) {
                    return response;
                }
                else if (response == CommandResponse.END_EVENT) {
                    return CommandResponse.DECLINED;
                }
                else if (response != CommandResponse.ACCEPTED) {
                    command = (AbstractStrategyCommand) requestCommand(player, "correct construction");
                }
            }
        }

        return CommandResponse.ACCEPTED;
    }

    @Override
    public AbstractCommand getExitCommand() {
        return new AbstractStrategyCommand(this) {
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

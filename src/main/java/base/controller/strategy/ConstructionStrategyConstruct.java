package base.controller.strategy;

import base.controller.CommandResponse;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import board.Planet;
import player.Player;

import java.util.ArrayList;

public class ConstructionStrategyConstruct extends AbstractStrategyCommand {

    private GameObjectTarget planetTarget;
    private ArrayList<String> unitNames;

    public ConstructionStrategyConstruct(ConstructionStrategy strategy) {
        super(strategy);
        this.planetTarget = null;
        this.unitNames = new ArrayList<>();
    }

    public ConstructionStrategyConstruct(ConstructionStrategy strategy, GameObjectTarget planetTarget,
                                         ArrayList<String> unitNames) {
        super(strategy);
        this.planetTarget = planetTarget;
        this.unitNames = unitNames;
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        planetTarget = userInterface.requestTarget("planet");
        int unitCount = userInterface.requestNumber("unit count");
        for (int idx = 0; idx != unitCount; ++idx) {
            String unitName = userInterface.requestName("unit name");
            unitNames.add(unitName);
        }

        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        Planet planet = null;

        try {
            planet = (Planet) getController().getGameState().getObject(planetTarget);
        }
        catch (Exception exception) {
            getController().getUserInterface().reportError("invalid target: " + exception.getMessage());
            return CommandResponse.DECLINED;
        }

        boolean canConstruct = ((ConstructionStrategy) getController()).construct(player, planet, unitNames);
        if (!canConstruct) {
            return CommandResponse.DECLINED;
        }

        return CommandResponse.ACCEPTED;
    }
}

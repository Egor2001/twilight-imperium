package base.controller.invasion;

import base.controller.CommandResponse;
import base.controller.groundCombat.GroundCombatController;
import base.model.Dice;
import base.user.CommandRequestable;
import base.view.MessageString;
import player.Player;
import player.units.Ships.Ship;
import player.units.Unit;

import java.util.ArrayList;

public class PlayerInvasionMakeRoll extends PlayerInvasionCommand {
    public PlayerInvasionMakeRoll(InvasionController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        controller.getUserInterface().displayView(new MessageString("processing INVASION command: MAKE ROLLS"));
        Dice dice = new Dice();
        ArrayList<Unit> units = ((InvasionController) controller).GetBombardmentManager().getUnitsList();

        int numDices = 0;
        for (Unit unit: units) {
            numDices += ((Ship) unit).getNumberOfDicesForBombardment();
        }

        ArrayList<Integer> diceValues = new ArrayList<>();
        for (int i = 0; i < numDices; ++i) {
            diceValues.add(dice.getValue());
        }

        int indexDice = 0;
        for (Unit unit: units) {
            int numHits = 0;
            for (int i = 0; i < ((Ship) unit).getNumberOfDicesForBombardment(); ++i, ++indexDice) {
                if (((Ship) unit).canHitFromBombardment(diceValues.get(indexDice))) {
                    ++numHits;
                }
            }
            ((InvasionController) controller).GetBombardmentManager().setHitFromUnit(unit, numHits);
        }

        controller.getUserInterface().displayView(new MessageString("You roll: " + diceValues.toString()));

        return CommandResponse.ACCEPTED;
    }
}

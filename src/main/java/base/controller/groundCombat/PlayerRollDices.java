package base.controller.groundCombat;

import base.controller.CommandResponse;
import base.model.Dice;
import base.user.CommandRequestable;
import base.user.GameObjectTarget;
import base.view.MessageString;
import player.Player;

import java.util.ArrayList;

public class PlayerRollDices extends PlayerGroundCombatCommand {
    private int numDices;

    public PlayerRollDices(GroundCombatController controller) {
        super(controller);
    }

    @Override
    public boolean inputCommand(CommandRequestable userInterface) {
        int numDices = userInterface.requestNumber("dices");
        return true;
    }

    @Override
    public CommandResponse execute(Player player) {
        controller.getUserInterface().displayView(new MessageString("processing GROUND COMBAT command: ROLL DICES"));
        Dice dice = new Dice();

        ArrayList<Integer> diceValues = new ArrayList<>();

        for (int i = 0; i < numDices; ++i) {
            diceValues.add(dice.getValue());
        }
        //controller.getUserInterface().displayView(new MessageString("Dice values: " + diceValues.toString()));

        if (((GroundCombatController) controller).getPdsForSpaceCannon().size() >=
                numDices + ((GroundCombatController) controller).getDiceValues().size()) {
            ((GroundCombatController) controller).getDiceValues().addAll(diceValues);
        } else {
            controller.getUserInterface().displayView(new MessageString("Ypu roll dices more then you can, try again"));
            return CommandResponse.DECLINED;
        }
        return CommandResponse.ACCEPTED;
    }
}

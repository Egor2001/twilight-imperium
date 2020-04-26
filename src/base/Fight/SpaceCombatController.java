package base.Fight;

import ArmyUnits.Unit;

import java.util.ArrayList;

public class SpaceCombatController {
    public SpaceCombatController() {
        first_player_announce_retreat = false;
        second_player_announce_retreat = false;
    }

    public void anti_fighter_barrage(/*ArrayList<Unit> first_army_units, ArrayList<Unit> second_army_units*/)
    {

    }

    public void make_combat_rolls()
    {

    }

    public void assign_hits()
    {

    }

    public void announce_retreat()
    {

    }

    public void retreat()
    {

    }

    boolean someone_need_to_retreat() {
        return first_player_announce_retreat || second_player_announce_retreat;
    }

    public void loop() {
        anti_fighter_barrage ();

        while (true)
        {
            announce_retreat();
            make_combat_rolls();
            assign_hits();

            if (someone_need_to_retreat()) {
                retreat();
            }
        }

    }

    private boolean first_player_announce_retreat;
    private boolean second_player_announce_retreat;
}

package base.controller.fight;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.controller.CommandResponse;
import base.controller.global.GlobalCommandController;
import base.model.Dice;
import base.model.GameState;
import base.user.CommandRequestable;
import board.TileObject;
import player.Player;
import player.units.Ships.Ship;
import player.units.Unit;
import board.TileArmyManager;

import java.util.ArrayList;
import java.util.Objects;

public class SpaceCombatController extends AbstractController {

    private Player retreatPlayer;

    private Player defender;
    private Player invader;

    private ArrayList<Unit> defenderShips;
    private ArrayList<Unit> invaderShips;

    boolean isActivePhase = true;
    int hitValue = 0;

    public SpaceCombatController(CommandRequestable userInterface, GlobalCommandController globalCommandController,
                                 Player defender, Player invader,
                                 TileArmyManager tileArmyManager, TileObject space) {
        super(userInterface, globalCommandController);
        super.putCommand("assign", new PlayerCombatAssign(this));
        super.putCommand("retreat", new PlayerCombatRetreat(this));

        this.retreatPlayer = null;

        this.defender = defender;
        this.invader = invader;

        this.defenderShips = new ArrayList<>();
        this.invaderShips = new ArrayList<>();

        ArrayList<Unit> spaceUnits = tileArmyManager.getUnit(space);
        for (Unit unit : spaceUnits) {
            if (unit.getRace() == defender.getRace()) {
                this.defenderShips.add(unit);
            }
            else {
                this.invaderShips.add(unit);
            }
        }
    }

    public Player getRetreatPlayer() {
        return retreatPlayer;
    }

    public int antiFighterBarrage() {
        int value = 0;
        Dice dice = new Dice();
        for (Unit unit : defenderShips) {
            if (unit instanceof Ship && ((Ship) unit).hasAntiFighterBarrage()) {
                if (dice.getValue() >= ((Ship) unit).getNumberOfDicesForAntiFighterBarrage()) {
                    ++value;
                }
            }
        }

        return value;
    }

    public boolean announceRetreat(Player player) {
        if (retreatPlayer == null) {
            retreatPlayer = player;
            return true;
        }

        return false;
    }

    public int makeCombatRolls(Player player) {
        ArrayList<Unit> playerShips = (player == defender ? defenderShips : invaderShips);

        int value = 0;
        Dice dice = new Dice();
        for (Unit unit : playerShips) {
            if (unit instanceof Ship) {
                if (dice.getValue() >= ((Ship) unit).getCombatNumDices()) {
                    ++value;
                }
            }
        }

        return value;
    }

    public void assignHits(Player player, ArrayList<Integer> hitIdxList) {
        if (player != defender && player != invader) {
            throw new IllegalArgumentException("player is no defender nor invader");
        }

        ArrayList<Unit> ships = (player == defender ? defenderShips : invaderShips);
        for (int idx : hitIdxList) {
            if (idx >= ships.size() || idx < 0 || ships.get(idx) == null){
                throw new IllegalArgumentException("ship " + idx + " does not exist");
            }
            else if (ships.get(idx) instanceof Ship) {
                Ship ship = (Ship) ships.get(idx);
                if (ship.canSustainDamaged() && !ship.isDamaged()) {
                    ship.takeDamaged();
                }
                else {
                    ships.set(idx, null);
                }
            }
            else {
                ships.set(idx, null);
            }
        }
        ships.removeIf(Objects::isNull);
    }

    @Override
    public boolean start() {
        AbstractCommand command = null;
        CommandResponse response = null;

        int defenderHit = makeCombatRolls(defender) + antiFighterBarrage();;
        int invaderHit = makeCombatRolls(invader);
        while (retreatPlayer == null)
        {
            hitValue = invaderHit;
            command = requestCommand(defender, "combat");
            response = CommandResponse.DECLINED;
            while (response != CommandResponse.ACCEPTED) {
                response = command.execute(defender);
            }

            hitValue = defenderHit;
            command = requestCommand(invader, "combat");
            response = CommandResponse.DECLINED;
            while (response != CommandResponse.ACCEPTED) {
                response = command.execute(invader);
            }

            if (retreatPlayer == null) {
                defenderHit = makeCombatRolls(defender);
                invaderHit = makeCombatRolls(invader);
            }
        }

        return true;
    }

    @Override
    public GameState getGameState() {
        return null;
    }
}

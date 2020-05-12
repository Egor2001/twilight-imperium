package base.controller.combat;

import base.controller.AbstractCommand;
import base.controller.AbstractController;
import base.controller.CommandResponse;
import base.controller.global.GlobalCommandController;
import base.controller.invasion.InvasionController;
import base.model.Dice;
import base.model.GameState;
import base.user.CommandRequestable;
import board.TileObject;
import player.Player;
import player.units.Ships.Ship;
import player.units.Unit;

import java.util.ArrayList;
import java.util.Objects;

public class SpaceCombatController extends AbstractController {

    public enum CombatPhase {
        COMBAT_BARRAGE("barrage"),
        COMBAT_RETREAT("retreat"),
        COMBAT_MAKE_ROLLS("make-rolls"),
        COMBAT_ASSIGN_HITS("assign-hits");

        public final String name;

        CombatPhase(String name) {
            this.name = name;
        }

        public static CombatPhase next(CombatPhase phase) {
            switch (phase) {
                case COMBAT_BARRAGE:
                    return COMBAT_RETREAT;
                case COMBAT_RETREAT:
                    return COMBAT_MAKE_ROLLS;
                case COMBAT_MAKE_ROLLS:
                    return COMBAT_ASSIGN_HITS;
                case COMBAT_ASSIGN_HITS:
                    return COMBAT_RETREAT;
            }

            return null;
        }
    }

    private GameState gameState;

    private CombatPhase combatPhase;
    private Player combatPlayer;

    private Player retreatPlayer;
    private TileObject retreatTile;

    private Player invader;
    private Player defender;

    private ArrayList<Unit> defenderShips;
    private ArrayList<Unit> invaderShips;

    private int invaderHitValue;
    private int defenderHitValue;

    private boolean isActivePhase = true;

    public SpaceCombatController(CommandRequestable userInterface, GameState gameState,
                                 GlobalCommandController globalCommandController, TileObject space, Player player) {
        super(userInterface, globalCommandController);
        super.putCommand("continue", new PlayerCombatContinue(this));
        super.putCommand("barrage", new PlayerCombatBarrage(this));
        super.putCommand("retreat", new PlayerCombatRetreat(this));
        super.putCommand("make-rolls", new PlayerCombatMakeRolls(this));
        super.putCommand("assign-hits", new PlayerCombatAssignHits(this));

        this.gameState = gameState;

        this.combatPhase = CombatPhase.COMBAT_BARRAGE;
        this.combatPlayer = defender;

        this.retreatPlayer = null;
        this.retreatTile = null;

        this.invader = player;
        this.defender = null;

        this.defenderShips = new ArrayList<>();
        this.invaderShips = new ArrayList<>();

        this.invaderHitValue = 0;
        this.defenderHitValue = 0;

        ArrayList<Unit> spaceUnits = this.gameState.getTileArmyManager().getUnit(space);
        for (Unit unit : spaceUnits) {
            if (unit.getRace() == player.getRace()) {
                this.invaderShips.add(unit);
            }
            else {
                defender = Player.getRacePlayerManager().getPlayer(unit.getRace());
                this.defenderShips.add(unit);
            }
        }
    }

    public CombatPhase getCombatPhase() {
        return combatPhase;
    }

    public int getAssignValue() {
        return (combatPlayer == defender ? invaderHitValue : defenderHitValue);
    }

    public boolean announceRetreat(Player player, TileObject tileObject) {
        if (this.retreatPlayer != null) {
            return false;
        }

        this.retreatPlayer = player;
        this.retreatTile = tileObject;

        return true;
    }

    public int makeRolls(Player player) {
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

        if (player == defender) {
            defenderHitValue += value;
        }
        else {
            invaderHitValue += value;
        }

        return value;
    }

    public int assignHits(Player player, ArrayList<Integer> hitIdxList) {
        if (player != defender && player != invader) {
            throw new IllegalArgumentException("player is no defender nor invader");
        }

        int killedCnt = 0;
        ArrayList<Unit> ships = (player == defender ? defenderShips : invaderShips);
        for (int idx : hitIdxList) {
            if (killedCnt == ships.size()){
                return 0;
            }
            else if (ships.get(idx) instanceof Ship) {
                Ship ship = (Ship) ships.get(idx);
                if (ship.canSustainDamaged() && !ship.isDamaged()) {
                    ship.takeDamaged();
                }
                else {
                    player.delUnit(ships.get(idx));
                    getGameState().getTileArmyManager().remove(ships.get(idx));
                    ships.set(idx, null);
                    ++killedCnt;
                }
            }
            else {
                player.delUnit(ships.get(idx));
                getGameState().getTileArmyManager().remove(ships.get(idx));
                ships.set(idx, null);
                ++killedCnt;
            }
        }
        ships.removeIf(Objects::isNull);

        if (combatPlayer == defender) {
            invaderHitValue = 0;
        }
        else {
            defenderHitValue = 0;
        }

        return ships.size();
    }

    public int barrage(Player player) {
        if (player != defender) {
            return 0;
        }

        int value = 0;
        Dice dice = new Dice();
        for (Unit unit : defenderShips) {
            if (unit instanceof Ship && ((Ship) unit).hasAntiFighterBarrage()) {
                if (dice.getValue() >= ((Ship) unit).getNumberOfDicesForAntiFighterBarrage()) {
                    ++value;
                }
            }
        }
        defenderHitValue += value;

        return value;
    }

    private boolean retreat(Player player) {
        if (combatPhase != CombatPhase.COMBAT_RETREAT && retreatPlayer == null) {
            return false;
        }

        ArrayList<Unit> ships = (player == defender ? defenderShips : invaderShips);
        ArrayList<TileObject> way = new ArrayList<>();
        way.add(retreatTile);
        for (int idx = 0; idx != ships.size(); ++idx) {
            gameState.getTileArmyManager().move((Ship) ships.get(idx), way);
            ships.set(idx, null);
        }

        return true;
    }

    private CommandResponse nextCommand(Player player) {
        AbstractCommand command = null;
        CommandResponse response = null;

        command = requestCommand(player, "phase " + combatPhase.toString() + " combat");
        response = command.execute(player);
        while (response == CommandResponse.DECLINED) {
            command = requestCommand(player, "phase " + combatPhase.toString() + " correct combat");
            response = command.execute(player);
        }

        return response;
    }

    @Override
    public CommandResponse start() {
        CommandResponse response = null;

        if (defender == null) {
            return CommandResponse.ACCEPTED;
        }

        combatPhase = CombatPhase.COMBAT_BARRAGE;
        combatPlayer = defender;
        boolean stop = false;
        while (!stop)
        {
            response = nextCommand(combatPlayer);
            if (response == CommandResponse.END_GAME) {
                return response;
            }
            else if (response == CommandResponse.END_EVENT) {
                break;
            }

            if (combatPlayer == defender) {
                combatPlayer = invader;
            }
            else {
                combatPlayer = defender;
                combatPhase = CombatPhase.next(combatPhase);
            }
            stop = retreat(retreatPlayer);
        }

        InvasionController invasionController =
                new InvasionController(userInterface, gameState, globalCommandController, retreatTile.GetTile(), invader);

        return invasionController.start();
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public AbstractCommand getExitCommand() {
        return new PlayerCombatCommand(this) {
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

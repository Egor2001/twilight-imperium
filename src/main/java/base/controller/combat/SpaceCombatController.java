package base.controller.combat;

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

import java.util.ArrayList;
import java.util.Objects;

public class SpaceCombatController extends AbstractController {

    public enum CombatPhase {
        COMBAT_RETREAT(null),
        COMBAT_ASSIGN_HITS(CombatPhase.COMBAT_RETREAT),
        COMBAT_MAKE_ROLLS(CombatPhase.COMBAT_ASSIGN_HITS),
        COMBAT_BARRAGE(CombatPhase.COMBAT_MAKE_ROLLS);

        CombatPhase(CombatPhase next) {
            this.next = next;
        }

        public CombatPhase next;
    }

    private GameState gameState;

    private CombatPhase combatPhase;

    private Player retreatPlayer;
    private TileObject retreatTile;

    private Player invader;
    private Player defender;

    private ArrayList<Unit> defenderShips;
    private ArrayList<Unit> invaderShips;

    boolean isActivePhase = true;
    int hitValue = 0;

    public SpaceCombatController(CommandRequestable userInterface, GameState gameState,
                                 GlobalCommandController globalCommandController, TileObject space, Player player) {
        super(userInterface, globalCommandController);
        super.putCommand("continue", new PlayerCombatContinue(this));
        super.putCommand("retreat", new PlayerCombatRetreat(this));
        super.putCommand("make-rolls", new PlayerCombatMakeRolls(this));
        super.putCommand("assign-hits", new PlayerCombatAssignHits(this));

        this.gameState = gameState;

        this.retreatPlayer = null;
        this.retreatTile = null;

        this.invader = player;
        this.defender = null;

        this.defenderShips = new ArrayList<>();
        this.invaderShips = new ArrayList<>();

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

    public Player getRetreatPlayer() {
        return retreatPlayer;
    }

    public boolean announceRetreat(Player player, TileObject tileObject) {
        if (this.retreatPlayer == null) {
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
                    getGameState().getTileArmyManager().remove(ships.get(idx));
                    ships.set(idx, null);
                    ++killedCnt;
                }
            }
            else {
                getGameState().getTileArmyManager().remove(ships.get(idx));
                ships.set(idx, null);
                ++killedCnt;
            }
        }
        ships.removeIf(Objects::isNull);

        return ships.size();
    }

    private int barrage() {
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

    private boolean retreat(Player player) {
        if (retreatPlayer == null) {
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
        AbstractCommand command = null;
        CommandResponse response = null;

        if (defender == null) {
            return CommandResponse.ACCEPTED;
        }

        int defenderHit = makeRolls(defender) + barrage();;
        int invaderHit = makeRolls(invader);
        combatPhase = CombatPhase.COMBAT_BARRAGE;
        combatPhase = combatPhase.next;
        boolean stop = false;
        while (!stop)
        {
            hitValue = invaderHit;
            response = nextCommand(defender);
            invaderHit = 0;
            defenderHit += hitValue;
            if (response == CommandResponse.END_GAME) {
                return response;
            }
            else if (response == CommandResponse.END_EVENT) {
                break;
            }

            hitValue = defenderHit;
            response = nextCommand(invader);
            defenderHit = 0;
            invaderHit += hitValue;
            if (response == CommandResponse.END_GAME) {
                return response;
            }
            else if (response == CommandResponse.END_EVENT) {
                break;
            }

            stop = retreat(retreatPlayer);
            if (combatPhase == CombatPhase.COMBAT_RETREAT && !stop) {
                combatPhase = CombatPhase.COMBAT_MAKE_ROLLS;
            }
            else {
                combatPhase = combatPhase.next;
            }
        }

        return CommandResponse.ACCEPTED;
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

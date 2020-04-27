package base;

import base.controller.HierarchyController;
import base.model.Army;
import base.model.GameState;
import base.model.Player;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {

    private final InputStream inputStream = System.in;
    private final PrintStream printStream = System.out;
    private final Scanner inputScanner = new Scanner(inputStream);

    public UserInterface() {
    }

    public Player requestNewPlayer() {
        printStream.println("Welcome! insert your name:");
        String name = inputScanner.nextLine();
        printStream.println("insert your race:");
        String race = inputScanner.nextLine();

        return new Player(name, race);
    }

    public IPlayerStrategyCommand requestStrategy(final Player player) {
        assert (player != null) : "player is null";

        printStream.print("STRATEGY phase, player: ");
        printStream.println(player.getName());

        IPlayerStrategyCommand command = new CPlayerStrategyPick();
        while (!command.inputCommand()) {
            printStream.println("Invalid input. Try again.");
            printStream.flush();
        }

        return command;
    }

    public IPlayerActionCommand requestAction(final Player player) {
        assert (player != null) : "player is null";

        printStream.print("ACTION phase, player: ");
        printStream.println(player.getName());

        IPlayerActionCommand command = new CPlayerActionMove();
        while (!command.inputCommand()) {
            printStream.println("Invalid input. Try again.");
            printStream.flush();
        }

        return command;
    }

    public IPlayerStatusCommand requestStatus(final Player player) {
        assert (player != null) : "player is null";

        printStream.print("STATUS phase, player: ");
        printStream.println(player.getName());

        IPlayerStatusCommand command = new CPlayerStatusCompleteMission();
        while (!command.inputCommand()) {
            printStream.println("Invalid input. Try again.");
            printStream.flush();
        }

        return command;
    }

    public Boolean refresh(final GameState gameState) {
        return true;
    }

    public interface IPlayerCommand {
        Boolean inputCommand();
        void procCommand();
    }

    public interface IPlayerStrategyCommand extends IPlayerCommand {
    }

    public interface IPlayerActionCommand extends IPlayerCommand {
    }

    public interface IPlayerStatusCommand extends IPlayerCommand {
    }

    public class CPlayerActionMove implements IPlayerActionCommand {

        public Integer shipIdx = 0;
        public Integer spaceIdx = 0;

        public CPlayerActionMove() {
            this.shipIdx = 0;
            this.spaceIdx = 0;
        }

        public CPlayerActionMove(Integer shipIdx, Integer spaceIdx) {
            this.shipIdx = shipIdx;
            this.spaceIdx = spaceIdx;
        }

        @Override
        public Boolean inputCommand() {
            try {
                printStream.println("enter ship number:");
                shipIdx = inputScanner.nextInt();
                printStream.println("enter destination space number:");
                spaceIdx = inputScanner.nextInt();
            } catch (InputMismatchException exception) {
                printStream.flush();
                return false;
            }

            return true;
        }

        @Override
        public void procCommand() {
            printStream.println("processing ACTION command: MOVE");
        }
    }

    public class CPlayerStrategyPick implements IPlayerStrategyCommand {

        public Integer strategyIdx = 0;

        public CPlayerStrategyPick() {
            this.strategyIdx = 0;
        }

        public CPlayerStrategyPick(Integer strategyIdx) {
            this.strategyIdx = strategyIdx;
        }

        @Override
        public Boolean inputCommand() {
            try {
                printStream.println("enter strategy number:");
                strategyIdx = inputScanner.nextInt();
            } catch (InputMismatchException exception) {
                printStream.flush();
                return false;
            }

            return true;
        }

        @Override
        public void procCommand() {
            printStream.println("processing STRATEGY command: PICK");
        }
    }

    public class CPlayerStatusCompleteMission implements IPlayerStatusCommand {

        public Integer missionIdx = 0;

        public CPlayerStatusCompleteMission() {
            this.missionIdx = 0;
        }

        public CPlayerStatusCompleteMission(Integer missionIdx) {
            this.missionIdx = missionIdx;
        }

        @Override
        public Boolean inputCommand() {
            try {
                printStream.println("enter mission number:");
                missionIdx = inputScanner.nextInt();
            } catch (InputMismatchException exception) {
                printStream.flush();
                return false;
            }

            return true;
        }

        @Override
        public void procCommand() {
            printStream.println("processing STATUS command: PICK");
        }
    }
}

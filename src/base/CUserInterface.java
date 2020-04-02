package base;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CUserInterface {

    private final InputStream inputStream = System.in;
    private final PrintStream printStream = System.out;
    private final Scanner inputScanner = new Scanner(inputStream);

    public CUserInterface() {
    }

    public CPlayer requestNewPlayer() {
        printStream.println("Welcome! insert your name:");
        String name = inputScanner.nextLine();

        return new CPlayer(name, new CArmy());
    }

    public IPlayerCommand requestAction(final CPlayer player) {
        assert (player != null) : "player is null";

        printStream.print("Action phase, player: ");
        printStream.println(player.getName());

        IPlayerCommand command = new CPlayerActionMove();
        while (!command.inputCommand()) {
            printStream.println("Invalid input. Try again.");
            printStream.flush();
        }

        return command;
    }

    public Boolean refresh(final CGameState gameState) {
        return true;
    }

    public interface IPlayerCommand {
        Boolean inputCommand();
        void procCommand();
    }

    public class CPlayerActionMove implements IPlayerCommand {

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
            printStream.println("processing command");
        }

        public Integer shipIdx = 0;
        public Integer spaceIdx = 0;
    }
}

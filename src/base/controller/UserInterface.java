package base.controller;

import java.io.*;
import java.util.Scanner;

public class UserInterface implements CommandRequestable {

    private InputStream inputStream;
    private PrintStream printStream;
    private Scanner inputScanner;

    public UserInterface(InputStream inputStream, PrintStream printStream) {
        this.inputStream = inputStream;
        this.printStream = printStream;
        this.inputScanner = new Scanner(this.inputStream);
    }

    @Override
    public int requestNumber(String purpose) {
        printStream.println("Input " + purpose + " number");
        String input = inputScanner.next();

        boolean isNumber = input.matches("\\d+");
        while (!isNumber) {
            printStream.println("Error: \"" + input + "\" is not a valid number");
            input = inputScanner.next();
            isNumber = input.matches("\\d+");
        }

        return Integer.decode(input);
    }

    @Override
    public String requestName(String purpose) {
        printStream.println("Input " + purpose + " name");
        String input = inputScanner.next();

        boolean isName = input.matches("[-\\w]+");
        while (!isName) {
            printStream.println("Error: \"" + input + "\" is not a valid name");
            input = inputScanner.next();
            isName = input.matches("[-\\w]+");
        }

        return input;
    }

    @Override
    public HierarchyController.GameObjectTarget requestTarget(String purpose) {
        printStream.println("Input " + purpose + " target");
        String input = "";

        HierarchyController.GameObjectTarget target = null;
        while (target == null) {
            try {
                input = inputScanner.next();
                target = HierarchyController.parseTarget(input);
            } catch (IllegalArgumentException exception) {
                printStream.println("Error: \"" + input + "\" is not a valid target");
                target = null;
            }
        }

        return target;
    }

    @Override
    public void reportError(String error) {
        printStream.println("Error: " + error);
    }
}

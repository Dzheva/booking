package cli;

import cli.constants.ApplicationString;

import java.util.Scanner;

public class IOHandler {
    private final Scanner scanner;

    public IOHandler() {
        scanner = new Scanner(System.in);
    }

    public void print(String message) {
        System.out.print(message);
    }

    public void printLine(String message) {
        System.out.println(message);
    }

    public void printNewLine() {
        System.out.println();
    }

    public int getInput(String message) {
        print(appendInputSuffix(message));
        while (!scanner.hasNextInt()) {
            print(appendInputSuffix(ApplicationString.BAD_INTEGER));
            scanner.next();
        }
        return scanner.nextInt();
    }

    private String appendInputSuffix(String message) {
        return message + ": ";
    }
}

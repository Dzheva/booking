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

    public void printLine(Object object) {
        printLine(String.valueOf(object));
    }

    public void printNewLine() {
        System.out.println();
    }

    public String getStringInput(String message) {
        print(appendInputSuffix(message));
        return scanner.nextLine();
    }

    public int getIntegerInput(String message) {
        print(appendInputSuffix(message));
        while (!scanner.hasNextInt()) {
            print(appendInputSuffix(ApplicationString.BAD_INTEGER));
            scanner.nextLine();
        }
        int nextInt = scanner.nextInt();
        scanner.nextLine();
        return nextInt;
    }

    private String appendInputSuffix(String message) {
        return message + ": ";
    }
}

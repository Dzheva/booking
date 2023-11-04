package cli;

import cli.constants.ApplicationString;
import cli.constants.Command;

import java.util.Map;

public class Application {
    private final IOHandler ioHandler;
    private boolean isRunning;
    private final Map<Integer, Runnable> commands = Map.of(
            Command.SHOW_FLIGHTS, this::showFlights,
            Command.SHOW_FLIGHT_INFORMATION, this::showFlightInformation,
            Command.RESERVE_FLIGHT, this::reserveFlight,
            Command.CANCEL_FLIGHT_RESERVATION, this::cancelFlightReservation,
            Command.SHOW_RESERVED_FLIGHTS, this::showReservedFlights,
            Command.EXIT, () -> isRunning = false
    );

    public Application() {
        ioHandler = new IOHandler();
    }

    public void run() {
        isRunning = true;

        while (isRunning) {
            ioHandler.printNewLine();
            ioHandler.printLine(ApplicationString.MENU_OPTIONS);
            int command = ioHandler.getInput(ApplicationString.PROMPT_COMMAND);
            commands.getOrDefault(command, this::handleUnknownCommand).run();
        }
    }

    private void showFlights() {

    }

    private void showFlightInformation() {

    }

    private void reserveFlight() {

    }

    private void cancelFlightReservation() {

    }

    private void showReservedFlights() {

    }

    private void handleUnknownCommand() {
        ioHandler.printLine(ApplicationString.BAD_COMMAND);
    }
}

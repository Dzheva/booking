package cli;

import cli.constants.ApplicationString;
import cli.constants.Command;
import controllers.FlightController;
import controllers.ReservationController;
import controllers.UserController;
import models.Flight;
import models.User;

import java.util.Map;
import java.util.Optional;

public class Application {
    private final FlightController flightController;
    private final UserController userController;
    private final ReservationController reservationController;
    private final IOHandler ioHandler;
    private User currentUser;
    private boolean isRunning;

    private final Map<Integer, Runnable> commands = Map.of(
            Command.SHOW_FLIGHTS, this::showFlights,
            Command.SHOW_FLIGHT_INFORMATION, this::showFlightInformation,
            Command.RESERVE_FLIGHT, this::reserveFlight,
            Command.CANCEL_FLIGHT_RESERVATION, this::cancelFlightReservation,
            Command.SHOW_RESERVED_FLIGHTS, this::showReservedFlights,
            Command.CHANGE_USER, this::changeCurrentUser,
            Command.EXIT, () -> isRunning = false
    );

    public Application() {
        ioHandler = new IOHandler();
        flightController = new FlightController();
        userController = new UserController();
        reservationController = new ReservationController();
    }

    public void start() {
        ioHandler.printLine(ApplicationString.AUTHORIZATION_MESSAGE);
        changeCurrentUser();
        run();
    }

    private void run() {
        isRunning = true;

        while (isRunning) {
            ioHandler.printNewLine();
            ioHandler.printLine(ApplicationString.MENU_OPTIONS);
            int command = ioHandler.getIntegerInput(ApplicationString.PROMPT_COMMAND);
            commands.getOrDefault(command, this::handleUnknownCommand).run();
        }
    }

    private void showFlights() {
        flightController.getAllFlights().forEach(ioHandler::printLine);
    }

    private void showFlightInformation() {
        int flightId = ioHandler.getIntegerInput(ApplicationString.PROMPT_FLIGHT_NUMBER);
        Flight flight = flightController.getFlightById(flightId);
        String message = flight == null ? ApplicationString.BAD_FLIGHT_NUMBER : String.valueOf(flight);
        ioHandler.printLine(message);
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

    private void changeCurrentUser() {
        while (true) {
            String login = ioHandler.getStringInput(ApplicationString.PROMPT_LOGIN);
            String password = ioHandler.getStringInput(ApplicationString.PROMPT_PASSWORD);

            Optional<User> foundUser = userController.findUser(login, password);
            if (foundUser.isPresent()) {
                currentUser = foundUser.get();
                break;
            }
            ioHandler.printLine(ApplicationString.BAD_CREDENTIALS);
            ioHandler.printNewLine();
        }
    }
}

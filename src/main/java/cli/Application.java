package cli;

import cli.constants.ApplicationString;
import cli.constants.Command;
import cli.constants.SubmenuCommand;
import cli.utilities.TableViewHandler;
import controllers.FlightController;
import controllers.ReservationController;
import controllers.UserController;
import models.Flight;
import models.Passenger;
import models.Reservation;
import models.User;
import utilities.RunnableChainBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

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
            Command.RESERVE_FLIGHT, this::findFlightsAndReserve,
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
        new RunnableChainBuilder(this::changeCurrentUser).add(this::run).execute();
    }

    private void run() {
        isRunning = true;

        while (isRunning) {
            ioHandler.printNewLine();
            ioHandler.printLine(ApplicationString.MENU_OPTIONS);
            int command = ioHandler.getIntegerInput(ApplicationString.PROMPT_COMMAND);
            commands.getOrDefault(command, this::handleUnknownCommand).run();
            saveAllData();
        }
    }

    private void showFlights() {
        ioHandler.printLine(TableViewHandler.toFlightTable(flightController.getAllFlights()));
    }

    private void showFlightInformation() {
        int flightId = ioHandler.getIntegerInput(ApplicationString.PROMPT_FLIGHT_ID);
        Flight flight = flightController.getFlight(flightId);
        ioHandler.printLine(
                flight == null ? ApplicationString.BAD_FLIGHT_ID : TableViewHandler.toFlightTable(flight));
    }

    private void findFlightsAndReserve() {
        String destination = ioHandler.getStringInput(ApplicationString.PROMPT_FLIGHT_DESTINATION);
        int additionalPassengers =
                ioHandler.getIntegerInput(ApplicationString.PROMPT_QUANTITY_OF_ADDITIONAL_PASSENGERS);
        List<Flight> flights = flightController.findFlights(destination.trim(), additionalPassengers);
        if (!flights.isEmpty()) {
            ioHandler.printLine(TableViewHandler.toFlightTable(flights));
        } else {
            ioHandler.printLine(ApplicationString.NO_FLIGHTS_FOUND_MESSAGE);
        }
        startReservationSubmenu(additionalPassengers);
    }

    private void startReservationSubmenu(int numberOfAdditionalPassengers) {
        while (true) {
            ioHandler.printNewLine();
            ioHandler.printLine(ApplicationString.SUBMENU_OPTIONS);
            int command = ioHandler.getIntegerInput(ApplicationString.PROMPT_COMMAND);

            if (command == SubmenuCommand.RESERVE_FLIGHT) {
                reserveFlight(numberOfAdditionalPassengers);
            } else if (command == SubmenuCommand.RETURN) {
                break;
            } else {
                ioHandler.printLine(ApplicationString.BAD_COMMAND);
            }
        }
    }

    private void reserveFlight(int numberOfPassengers) {
        int flightId = ioHandler.getIntegerInput(ApplicationString.PROMPT_FLIGHT_ID);
        Flight flight = flightController.getFlight(flightId);
        if (flight == null || flight.getSeatsAvailable() < numberOfPassengers + 1) {
            ioHandler.printLine(ApplicationString.RESERVATION_IS_NOT_POSSIBLE_MESSAGE);
            return;
        }

        List<Passenger> passengers = new ArrayList<>();
        IntStream.range(0, numberOfPassengers).forEach(ignore -> {
            String firstName = ioHandler.getStringInput(ApplicationString.PROMPT_PASSENGER_NAME);
            String lastName = ioHandler.getStringInput(ApplicationString.PROMPT_PASSENGER_SURNAME);
            passengers.add(new Passenger(firstName, lastName));
        });
        reservationController.createReservation(flight, currentUser, passengers);
        ioHandler.printLine(ApplicationString.RESERVATION_CREATED_MESSAGE);
    }

    private void cancelFlightReservation() {
        int reservationId = ioHandler.getIntegerInput(ApplicationString.PROMPT_RESERVATION_ID);
        Optional<Reservation> optional = reservationController.getUserReservation(currentUser.id(), reservationId);

        optional.ifPresentOrElse(reservation -> {
            Flight flight = flightController.getFlight(reservation.flightId());
            reservationController.cancelReservation(reservation, flight);
            ioHandler.printLine(ApplicationString.RESERVATION_CANCELLED_MESSAGE);
        }, () -> ioHandler.printLine(ApplicationString.BAD_RESERVATION_ID));
    }

    private void showReservedFlights() {
        List<Reservation> reservations = reservationController.getUserReservations(currentUser.id());
        if (reservations.isEmpty()) ioHandler.printLine(ApplicationString.NO_RESERVATIONS_FOUND_MESSAGE);
        for (int i = 0; i < reservations.size(); i++) {
            ioHandler.printLine(TableViewHandler.toReservationTable(reservations.get(i)));
            if (i != reservations.size() - 1) ioHandler.printNewLine();
        }
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

    private void handleUnknownCommand() {
        ioHandler.printLine(ApplicationString.BAD_COMMAND);
    }

    private void saveAllData() {
        new RunnableChainBuilder(userController::saveAll)
                .add(reservationController::saveAll).add(flightController::saveAll).execute();
    }
}

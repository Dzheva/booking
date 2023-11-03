package StepProject.consoleUI;

import StepProject.controller.FlightController;
import StepProject.controller.ReservationController;

import java.util.Scanner;

public class ConsoleUI {
    private final FlightController flightController;
    private final ReservationController reservationController;
    private final Scanner scanner;

    public ConsoleUI() {
        this.flightController = new FlightController();
        this.reservationController = new ReservationController();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Головне меню:");
            System.out.println("1. Онлайн-табло");
            System.out.println("2. Подивитися інформацію про рейс");
            System.out.println("3. Пошук та бронювання рейсу");
            System.out.println("4. Скасувати бронювання");
            System.out.println("5. Мої рейси");
            System.out.println("6. Вихід");

            int choice = getIntInput("Виберіть опцію: ");

            switch (choice) {
                case 1:
                    displayFlights();
                    break;
                case 2:
                    viewFlightInformation();
                    break;
                case 3:
                    searchAndBookFlight();
                    break;
                case 4:
                    cancelReservation();
                    break;
                case 5:
                    viewMyFlights();
                    break;
                case 6:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
                    break;
            }
        }

        System.out.println("До побачення!");
    }

    private void displayFlights() {
        // Реализуйте логику для отображения онлайн-табло рейсов
    }

    private void viewFlightInformation() {
        // Реализуйте логику для просмотра информации о рейсе
    }

    private void searchAndBookFlight() {
        // Реализуйте логику для поиска и бронирования рейса
    }

    private void cancelReservation() {
        // Реализуйте логику для отмены бронирования
    }

    private void viewMyFlights() {
        // Реализуйте логику для просмотра своих рейсов
    }

    private int getIntInput(String prompt) {
        int input = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt);

            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                validInput = true;
            } else {
                System.out.println("Невірний формат вводу. Будь ласка, введіть ціле число.");
                scanner.next(); // Очистка буфера
            }
        }

        return input;
    }

}

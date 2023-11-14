package cli.constants;

public final class ApplicationString {
    public static final String MENU_OPTIONS = """
            Головне меню:
            1. Онлайн-табло
            2. Подивитися інформацію про рейс
            3. Пошук та бронювання рейсу
            4. Скасувати бронювання
            5. Мої бронювання
            6. Зміна користувача
            7. Вихід""";
    public static final String SUBMENU_OPTIONS = """
            1 - Забронювати рейс
            2 - Повернутися до головного меню""";
    public static final String AUTHORIZATION_MESSAGE = "Зайдіть в свій обліковий запис для використання додатка";
    public static final String NO_FLIGHTS_FOUND_MESSAGE = "Не знайдено жодного рейса";
    public static final String RESERVATION_CANCELLED_MESSAGE = "Поточне бронювання було скасовано";
    public static final String RESERVATION_CREATED_MESSAGE = "Поточний рейс було заброньовано";
    public static final String RESERVATION_IS_NOT_POSSIBLE_MESSAGE = "Неможливо забронювати поточний рейс";
    public static final String NO_RESERVATIONS_FOUND_MESSAGE = "Не знайдено жодного бронювання";
    public static final String BAD_INTEGER = "Будь ласка, введіть ціле число";
    public static final String BAD_COMMAND = "Команду не знайдено. Спробуйте ще раз";
    public static final String BAD_FLIGHT_ID = "Поточний рейс не знайдено";
    public static final String BAD_RESERVATION_ID = "Поточне бронювання не знайдено";
    public static final String BAD_CREDENTIALS = "Користувача не знайдено. Спробуйте ще раз";
    public static final String PROMPT_COMMAND = "Введіть команду";
    public static final String PROMPT_LOGIN = "Введіть логін користувача";
    public static final String PROMPT_PASSWORD = "Введіть пароль";
    public static final String PROMPT_FLIGHT_ID = "Введіть номер рейсу";
    public static final String PROMPT_RESERVATION_ID = "Введіть номер бронювання";
    public static final String PROMPT_QUANTITY_OF_ADDITIONAL_PASSENGERS = "Введіть кількість додаткових пасажирів";
    public static final String PROMPT_PASSENGER_NAME = "Введіть ім'я особи";
    public static final String PROMPT_PASSENGER_SURNAME = "Введіть прізвище особи";
    public static final String PROMPT_FLIGHT_DESTINATION = "Введіть місце призначення";
}

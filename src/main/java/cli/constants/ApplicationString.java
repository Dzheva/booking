package cli.constants;

public final class ApplicationString {
    public static final String MENU_OPTIONS = """
            Головне меню:
            1. Онлайн-табло
            2. Подивитися інформацію про рейс
            3. Пошук та бронювання рейсу
            4. Скасувати бронювання
            5. Мої рейси
            6. Зміна користувача
            7. Вихід""";

    public static final String AUTHORIZATION_MESSAGE = "Зайдіть в свій обліковий запис для використання додатка";
    public static final String BAD_INTEGER = "Будь ласка, введіть ціле число";
    public static final String BAD_COMMAND = "Команду не знайдено. Спробуйте ще раз";
    public static final String BAD_FLIGHT_NUMBER = "Поточний рейс не знайдено";
    public static final String BAD_CREDENTIALS = "Користувача не знайдено. Спробуйте ще раз";
    public static final String PROMPT_COMMAND = "Введіть команду";
    public static final String PROMPT_LOGIN = "Введіть логін користувача";
    public static final String PROMPT_PASSWORD = "Введіть пароль";
    public static final String PROMPT_FLIGHT_NUMBER = "Введіть номер рейсу";
}

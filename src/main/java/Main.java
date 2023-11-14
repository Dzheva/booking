import cli.Application;
import controllers.UserController;

public class Main {
    public static void main(String[] args) {
        UserController controller = new UserController();
        controller.createUser("herbert", "password", "Herbert", "Wells");
        controller.createUser("guest", "guest", "guest", "guest");
        controller.saveAll();
        new Application().start();
    }
}

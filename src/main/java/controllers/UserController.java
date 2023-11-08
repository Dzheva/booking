package controllers;

import models.User;
import services.UserService;

import java.util.Optional;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public Optional<User> findUser(String login, String password) {
        return userService.findUser(login, password);
    }

    public void createUser(String login, String password, String firstName, String lastName) {
        userService.createUser(login, password, firstName, lastName);
    }
}

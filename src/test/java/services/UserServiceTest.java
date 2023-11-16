package services;

import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService;
    String login = "login1";
    String password = "pass2";
    String firstName = "Bob";
    String lastName = "Franklin";

    Optional<User> createdUser;

    @BeforeEach
    void setUp(){
        userService = new UserService();
        userService.createUser(login, password, firstName, lastName);
        createdUser = userService.findUser(login, password);
    }

    @Test
    void findUser() {
        assertAll(
                () -> assertFalse(createdUser.isEmpty()),
                () -> assertTrue(createdUser.get().firstName().equals(firstName)),
                () -> assertTrue(createdUser.get().lastName().equals(lastName))
        );
    }

    @Test
    void createUser() {
        assertAll(
                () -> assertTrue(createdUser.get().login().equals(login)),
                () -> assertTrue(createdUser.get().password().equals(password)),
                () -> assertTrue(createdUser.get().firstName().equalsIgnoreCase("BOB"))
        );
    }
}
package services;

import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {
    private UserService userService;
    private User expectedUser;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        expectedUser =
                new User(-1, "sampleLogin", "samplePassword", "Bob", "Franklin");
        userService.createUser(
                expectedUser.login(), expectedUser.password(), expectedUser.firstName(), expectedUser.lastName());
    }

    @Test
    void findUser() {
        Optional<User> user = userService.findUser(expectedUser.login(), expectedUser.password());
        assertTrue(user.isPresent());
    }

    @Test
    void createUser() {
        Optional<User> user = userService.findUser(expectedUser.login(), expectedUser.password());
        assertTrue(user.isPresent());
        assertEquals(expectedUser.login(), user.get().login());
        assertEquals(expectedUser.password(), user.get().password());
        assertTrue(user.get().firstName().equalsIgnoreCase("BOB"));
    }
}

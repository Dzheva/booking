package daos;

import constants.ResourceName;
import models.User;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.ResourceHandler;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDAOTest {
    private UserDAO userDAO;
    private User user;

    @AfterAll
    public static void afterAllTests() {
        try {
            FileUtils.delete(new File(ResourceHandler.getResourcePath(ResourceName.USERS)));
        } catch (IOException ignore) {
        }
    }

    @BeforeEach
    void setUp() {
        userDAO = new UserDAO();
        user = new User(
                userDAO.getNextId(), "test1", "", "name", "lastName");
        userDAO.create(user);
    }

    @Test
    void create() {
        assertTrue(userDAO.getAll().contains(user));
    }

    @Test
    void get() {
        assertEquals(userDAO.get(user.id()), user);
        assertEquals(userDAO.get(user.id()).firstName(), user.firstName());
        assertEquals(userDAO.get(user.id()).lastName(), user.lastName());
    }

    @Test
    void getAll() {
        User newUser = new User(
                userDAO.getNextId(), "test2", "", "name", "lastName");
        userDAO.create(newUser);
        assertTrue(userDAO.getAll().contains(user));
        assertTrue(userDAO.getAll().contains(newUser));
        assertTrue(userDAO.getAll().size() >= 2);
    }

    @Test
    void getNextId() {
        assertEquals(userDAO.getAll().size() + 1, userDAO.getNextId());
    }

    @Test
    void saveAll() {
        userDAO.saveAll();
        int currentSize = userDAO.getAll().size();
        assertEquals(currentSize, new UserDAO().getAll().size());
    }
}

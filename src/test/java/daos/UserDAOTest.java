package daos;

import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private UserDAO userDAO;

    private User testUser;

    @BeforeEach
    void setUp(){
        userDAO = new UserDAO();
        testUser = new User(userDAO.getNextId(), "abc", "123", "TestName", "TestLastName");
        userDAO.create(testUser);
    }

    @Test
    void create() {
        assertAll(
                () -> assertTrue(userDAO.getAll().contains(testUser)),
                () -> assertFalse(userDAO.getAll().contains(null))
        );
    }

    @Test
    void get() {
        assertAll(
                () -> assertEquals(userDAO.get(testUser.id()),testUser),
                () -> assertEquals(userDAO.get(testUser.id()).firstName(), testUser.firstName()),
                () -> assertEquals(userDAO.get(testUser.id()).lastName(), testUser.lastName())
        );
    }

    @Test
    void getAll() {
        assertAll(
                () -> assertTrue(userDAO.getAll().contains(testUser)),
                () -> assertEquals(userDAO.getNextId()-1, userDAO.getAll().size())
        );
    }

    @Test
    void getNextId(){
        int actual = userDAO.getNextId();
        int expected = userDAO.getAll().size() + 1;

        assertEquals(expected, actual);
    }

    @Test
    void saveAll(){
        userDAO.saveAll();
        int size1 = userDAO.getAll().size();

        UserDAO userDAO2 = new UserDAO();
        int size2 = userDAO2.getAll().size();

        assertEquals(size1, size2);
    }

}
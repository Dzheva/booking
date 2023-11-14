package services;

import daos.UserDAO;
import models.User;

import java.util.Optional;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public Optional<User> findUser(String login, String password) {
        return userDAO.getAll().stream()
                .filter(user -> user.login().equals(login) && user.password().equals(password)).findFirst();
    }

    public void createUser(String login, String password, String firstName, String lastName) {
        if (userDAO.getAll().stream().anyMatch(user -> user.login().equals(login))) return;
        User user = new User(userDAO.getNextId(), login, password, firstName, lastName);
        userDAO.create(user);
    }

    public void saveAll() {
        userDAO.saveAll();
    }
}

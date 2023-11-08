package services;

import daos.UserDAO;
import models.User;

import java.util.ArrayList;
import java.util.Optional;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public Optional<User> findUser(String login, String password) {
        return userDAO.getAllUsers()
                .stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst();
    }

    public void createUser(String login, String password, String firstName, String lastName) {
        User user = new User(login, password, firstName, lastName, new ArrayList<>());
        userDAO.addUser(user);
    }
}

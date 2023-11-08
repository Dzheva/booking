package daos;

import models.User;
import utilities.ResourceHandler;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final String FILENAME = "users.ser";
    private final List<User> users;

    @SuppressWarnings("unchecked")
    public UserDAO() {
        Object object = ResourceHandler.loadData(FILENAME);
        users = object == null ? new ArrayList<>() : (List<User>) object;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
        ResourceHandler.saveData(FILENAME, users);
    }

    public User getUserById(int id) {
        return (id >= 0 && users.size() > id) ? users.get(id) : null;
    }
}

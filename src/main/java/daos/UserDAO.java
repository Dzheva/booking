package daos;

import models.User;
import utilities.ResourceHandler;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final String FILENAME = "users.ser";
    private List<User> users;

    public UserDAO() {
        this.users = new ArrayList<>();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserById(int id) {
        return (id >= 0 && users.size() > id) ? users.get(id) : null;
    }

    public void saveData() {
        ResourceHandler.saveData(FILENAME, users);
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        Object data = ResourceHandler.loadData(FILENAME);
        if (data != null) users = (List<User>) data;
    }
}

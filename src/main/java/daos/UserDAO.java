package daos;

import constants.ResourceName;
import models.User;
import utilities.ResourceHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class UserDAO implements FileDao<User> {
    private final List<User> users;
    private int nextUserId = 1;

    @SuppressWarnings("unchecked")
    public UserDAO() {
        Optional<Object> optional = ResourceHandler.loadData(ResourceName.USERS);
        users = optional.map(object -> (List<User>) object).orElseGet(ArrayList::new);

        if (!users.isEmpty()) {
            users.stream().max(Comparator.comparing(User::id))
                    .ifPresent(user -> nextUserId = user.id() + 1);
        }
    }

    @Override
    public void create(User user) {
        users.add(user);
    }

    @Override
    public User get(int id) {
        return users.stream().filter(user -> user.id() == id).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public int getNextId() {
        return nextUserId++;
    }

    public void saveAll() {
        ResourceHandler.saveData(ResourceName.USERS, users);
    }
}

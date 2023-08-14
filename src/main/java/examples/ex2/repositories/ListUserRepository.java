package examples.ex2.repositories;

import examples.ex2.models.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ListUserRepository implements IUserRepository {
    private final List<User> users = new LinkedList<>();

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public Optional<User> getUserById(int id) {
        return users.stream()
                .filter(u -> u.id() == id)
                .findFirst();
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}

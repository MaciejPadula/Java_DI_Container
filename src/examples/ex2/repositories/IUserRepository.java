package examples.ex2.repositories;

import examples.ex2.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    List<User> getAllUsers();
    Optional<User> getUserById(int id);
    void addUser(User user);
}

package examples.ex2;

import examples.ex2.models.User;
import examples.ex2.repositories.IUserRepository;

import java.util.List;

public class UserGetter {
    private final IUserRepository userRepository;

    public UserGetter(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> get() {
        return userRepository.getAllUsers();
    }
}

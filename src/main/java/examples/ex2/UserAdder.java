package examples.ex2;

import examples.ex2.models.User;
import examples.ex2.repositories.IUserRepository;

public class UserAdder {
    private final IUserRepository userRepository;

    public UserAdder(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(String name){
        this.userRepository.addUser(new User(0, name));
    }
}

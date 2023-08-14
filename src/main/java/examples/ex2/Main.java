package examples.ex2;

import com.github.mp.dependencyinjection.IServiceCollection;
import com.github.mp.dependencyinjection.ServiceCollection;
import com.github.mp.dependencyinjection.exceptions.ServiceNotFoundException;
import examples.ex2.repositories.IUserRepository;
import examples.ex2.repositories.ListUserRepository;

public class Main {
    public static void main(String[] args) throws ServiceNotFoundException {
        IServiceCollection collection = new ServiceCollection();
        collection.addScoped(IUserRepository.class, ListUserRepository.class);
        collection.addScoped(UserAdder.class);
        collection.addScoped(UserGetter.class);
        collection.addScoped(Flow.class);

        var provider = collection.buildServiceProvider();
        var flow = provider.getRequiredService(Flow.class);
        flow.run();
    }
}

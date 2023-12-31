package examples.ex1;

import com.github.mp.dependencyinjection.ServiceCollection;
import com.github.mp.dependencyinjection.exceptions.ServiceNotFoundException;

public class Main {
    public static void main(String[] args) throws ServiceNotFoundException {
        var collection = new ServiceCollection();
        collection.addScoped(IService.class, ServiceImpl.class);
        collection.addTransient(SecondWrapper.class, p ->
                new SecondWrapper(p.getService(IService.class)));

        collection.addTransient(Wrapper.class, p ->
                new Wrapper(p.getService(IService.class)));

        collection.addTransient(Flow.class);

        var provider = collection.buildServiceProvider();

        var service = provider.getRequiredService(Flow.class);
        service.print();

        service = provider.getRequiredService(Flow.class);
        service.print();
    }
}
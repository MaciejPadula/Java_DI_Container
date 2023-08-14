package examples.ex1;

import com.github.mp.dependencyinjection.services.ServiceCollection;
import com.github.mp.dependencyinjection.exceptions.ServiceNotFoundException;

public class Main {
    public static void main(String[] args) throws ServiceNotFoundException {
        var collection = new ServiceCollection();

        collection.addScoped(IService.class, ServiceImpl.class);
        collection.addTransient(Wrapper.class);
        collection.addTransient(SecondWrapper.class);
        collection.addTransient(Printer.class, provider ->
                new Printer(provider.getService(IService.class), "xd"));

        collection.addTransient(Flow.class);

        var provider = collection.buildServiceProvider();

        var service = provider.getRequiredService(Flow.class);
        service.print();

        service = provider.getRequiredService(Flow.class);
        service.print();
    }
}
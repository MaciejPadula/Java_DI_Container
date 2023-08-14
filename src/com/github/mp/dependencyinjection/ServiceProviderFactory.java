package com.github.mp.dependencyinjection;

import com.github.mp.dependencyinjection.handlers.ScopedHandler;
import com.github.mp.dependencyinjection.handlers.SingletonHandler;
import com.github.mp.dependencyinjection.models.Descriptor;
import com.github.mp.dependencyinjection.providers.TransactionProvider;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ServiceProviderFactory {
    public static IServiceProvider createWithDotNetLifeTimes(Map<Type, Descriptor> descriptors) {
        return new ServiceProvider(List.of(
                new ScopedHandler(),
                new SingletonHandler()
        ), new TransactionProvider(), descriptors);
    }
}

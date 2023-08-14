package com.github.mp.dependencyinjection;

import com.github.mp.dependencyinjection.exceptions.ServiceNotFoundException;
import com.github.mp.dependencyinjection.handlers.ISingleInstanceHandler;
import com.github.mp.dependencyinjection.models.Descriptor;
import com.github.mp.dependencyinjection.models.enums.Lifetime;
import com.github.mp.dependencyinjection.providers.ITransactionProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;

public class ServiceProvider implements IServiceProvider {
    private final List<ISingleInstanceHandler> handlers;
    private final ITransactionProvider transactionProvider;
    private final Map<Type, Descriptor> services;

    public ServiceProvider(List<ISingleInstanceHandler> handlers, ITransactionProvider transactionProvider, Map<Type, Descriptor> services) {
        this.handlers = handlers;
        this.transactionProvider = transactionProvider;
        this.services = services;
    }

    @Override
    public <T> T getService(Class<T> key) {
        var descriptor = services.get(key);
        var transactionId = transactionProvider.tryBeginTransaction();

        if (transactionProvider.isTransactionIdValid(transactionId)) {
            clearScopedHandler();
        }

        var instance = key.cast(createObject(key, descriptor));

        if (transactionProvider.tryFinalizeTransaction(transactionId)) {
            clearScopedHandler();
        }

        return instance;
    }

    private void clearScopedHandler() {
        handlers.stream()
                .filter(x -> x.canHandle(Lifetime.SCOPED))
                .findFirst()
                .ifPresent(ISingleInstanceHandler::clear);
    }

    @Override
    public <T> T getRequiredService(Class<T> inter) throws ServiceNotFoundException {
        var service = getService(inter);

        if (service == null)
            throw new ServiceNotFoundException();

        return service;
    }

    private Object createObject(Type inter, Descriptor descriptor) {
        var handler = handlers.stream()
                .filter(x -> x.canHandle(descriptor.lifetime))
                .findFirst();

        return handler.isEmpty() ?
                instanceFactory(descriptor) :
                handler.get().tryGetOrCreateInstance(inter, () -> instanceFactory(descriptor));
    }

    private Object instanceFactory(Descriptor descriptor) {
        if (descriptor.factory != null) {
            return descriptor.factory.apply(this);
        }

        try {
            var constructors = Class.forName(descriptor.objectType.getTypeName()).getConstructors();
            var constructor = Arrays.stream(constructors).findFirst().get();
            var params = constructor.getParameterTypes();
            var arguments = new Object[params.length];

            for(int i=0; i < params.length; ++i){
                var param = Class.forName(params[i].getTypeName());
                var dependencyDescriptor = services.get(param);

                arguments[i] = createObject(param, dependencyDescriptor);
            }

            return constructor.newInstance(arguments);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException ignored) {
            return null;
        }
    }
}

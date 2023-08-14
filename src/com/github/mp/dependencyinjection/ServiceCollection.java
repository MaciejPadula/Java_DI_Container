package com.github.mp.dependencyinjection;

import com.github.mp.dependencyinjection.models.Descriptor;
import com.github.mp.dependencyinjection.models.enums.Lifetime;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ServiceCollection implements IServiceCollection{
    private final Map<Type, Descriptor> services;

    public ServiceCollection() {
        services = new HashMap<>();
    }

    @Override
    public IServiceCollection add(Descriptor descriptor) {
        return add(descriptor.objectType, descriptor);
    }

    @Override
    public IServiceCollection add(Type inter, Descriptor descriptor) {
        putNewObject(inter, descriptor);
        return this;
    }

    @Override
    public IServiceCollection addSingleton(Type inter, Type obj) {
        return add(inter, new Descriptor(Lifetime.SINGLETON, obj));
    }

    @Override
    public IServiceCollection addSingleton(Type obj) {
        return addSingleton(obj, obj);
    }

    @Override
    public IServiceCollection addSingleton(Type inter, Function<IServiceProvider, Object> factory) {
        var descriptor = new Descriptor(Lifetime.SINGLETON, inter)
                .withFactory(factory);

        return add(inter, descriptor);
    }

    @Override
    public IServiceCollection addTransient(Type inter, Type obj) {
        return add(inter, new Descriptor(Lifetime.TRANSIENT, obj));
    }

    @Override
    public IServiceCollection addTransient(Type obj) {
        return addTransient(obj, obj);
    }

    @Override
    public IServiceCollection addTransient(Type inter, Function<IServiceProvider, Object> factory) {
        var newInstance = new Descriptor(Lifetime.TRANSIENT, inter)
                .withFactory(factory);
        services.put(inter, newInstance);
        return this;
    }

    @Override
    public IServiceCollection addScoped(Type inter, Type obj) {
        return add(inter, new Descriptor(Lifetime.SCOPED, obj));
    }

    @Override
    public IServiceCollection addScoped(Type obj) {
        return addScoped(obj, obj);
    }

    @Override
    public IServiceCollection addScoped(Type inter, Function<IServiceProvider, Object> factory) {
        var newInstance = new Descriptor(Lifetime.SCOPED, inter)
                .withFactory(factory);
        services.put(inter, newInstance);
        return this;
    }

    @Override
    public IServiceProvider buildServiceProvider() {
        return ServiceProviderFactory.createWithDotNetLifeTimes(services);
    }

    private void putNewObject(Type key, Descriptor descriptor){
        if(!validateCast(key, descriptor.objectType)) throw new RuntimeException("NO!");
        if(services.containsKey(key)) throw new RuntimeException("Hey stop you stupid!!");

        services.put(key, descriptor);
    }

    private boolean validateCast(Type inter, Type obj){
        return inter.getClass().isAssignableFrom(obj.getClass());
    }
}

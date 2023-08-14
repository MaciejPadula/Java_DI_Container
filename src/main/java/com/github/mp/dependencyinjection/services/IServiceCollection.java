package com.github.mp.dependencyinjection.services;

import com.github.mp.dependencyinjection.models.Descriptor;

import java.lang.reflect.Type;
import java.util.function.Function;

public interface IServiceCollection {
    IServiceCollection add(Descriptor descriptor);
    IServiceCollection add(Type inter, Descriptor descriptor);

    IServiceCollection addSingleton(Type inter, Type obj);
    IServiceCollection addSingleton(Type obj);
    IServiceCollection addSingleton(Type inter, Function<IServiceProvider, Object> factory);

    IServiceCollection addTransient(Type inter, Type obj);
    IServiceCollection addTransient(Type obj);
    IServiceCollection addTransient(Type inter, Function<IServiceProvider, Object> factory);

    IServiceCollection addScoped(Type inter, Type obj);
    IServiceCollection addScoped(Type obj);
    IServiceCollection addScoped(Type inter, Function<IServiceProvider, Object> factory);

    IServiceProvider buildServiceProvider();

//    IServiceCollection configure(String filename, Type configType) throws IOException;
}

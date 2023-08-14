package com.github.mp.dependencyinjection.models;

import com.github.mp.dependencyinjection.services.IServiceProvider;
import com.github.mp.dependencyinjection.models.enums.Lifetime;

import java.lang.reflect.Type;
import java.util.function.Function;

public class Descriptor {
    public final Lifetime lifetime;
    public final Type objectType;
    public Function<IServiceProvider, Object> factory;

    public Descriptor(Lifetime lifetime, Type objectType) {
        this.lifetime = lifetime;
        this.objectType = objectType;
    }

    public Descriptor withFactory(Function<IServiceProvider, Object> factory){
        this.factory = factory;
        return this;
    }
}

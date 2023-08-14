package com.github.mp.dependencyinjection.handlers;

import com.github.mp.dependencyinjection.models.enums.Lifetime;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class MapHandlerBase implements ISingleInstanceHandler {
    private final Map<Type, Object> instances = new HashMap<>();
    private final Lifetime lifetime;

    public MapHandlerBase(Lifetime lifetime) {
        this.lifetime = lifetime;
    }

    @Override
    public boolean canHandle(Lifetime lifetime) {
        return this.lifetime == lifetime;
    }

    @Override
    public Object tryGetOrCreateInstance(Type key, Supplier<Object> supplier) {
        if (!instances.containsKey(key)) {
            instances.put(key, supplier.get());
        }

        return instances.get(key);
    }

    @Override
    public void clear() {
        this.instances.clear();
    }
}

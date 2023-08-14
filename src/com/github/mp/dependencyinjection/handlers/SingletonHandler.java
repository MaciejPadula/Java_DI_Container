package com.github.mp.dependencyinjection.handlers;

import com.github.mp.dependencyinjection.models.enums.Lifetime;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SingletonHandler implements ISingleInstanceHandler {
    private final Map<Type, Object> singletons = new HashMap<>();

    @Override
    public boolean canHandle(Lifetime lifetime) {
        return lifetime == Lifetime.SINGLETON;
    }

    @Override
    public Object tryGetOrCreateInstance(Type key, Supplier<Object> supplier) {
        if (!singletons.containsKey(key)) {
            singletons.put(key, supplier.get());
        }

        return singletons.get(key);
    }

    @Override
    public void clear() {

    }
}

package com.github.mp.dependencyinjection.handlers;

import com.github.mp.dependencyinjection.models.enums.Lifetime;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ScopedHandler implements ISingleInstanceHandler {
    private Map<Type, Object> scope = null;

    @Override
    public boolean canHandle(Lifetime lifetime) {
        return lifetime == Lifetime.SCOPED;
    }

    @Override
    public Object tryGetOrCreateInstance(Type key, Supplier<Object> supplier) {
        if (scope == null) {
            scope = new HashMap<>();
        }

        if (!scope.containsKey(key)) {
            scope.put(key, supplier.get());
        }

        return scope.get(key);
    }

    @Override
    public void clear() {
        scope = null;
    }
}

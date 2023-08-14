package com.github.mp.dependencyinjection.handlers;

import com.github.mp.dependencyinjection.models.enums.Lifetime;

import java.lang.reflect.Type;
import java.util.function.Supplier;

public interface ISingleInstanceHandler {
    boolean canHandle(Lifetime lifetime);
    Object tryGetOrCreateInstance(Type key, Supplier<Object> supplier);
    void clear();
}

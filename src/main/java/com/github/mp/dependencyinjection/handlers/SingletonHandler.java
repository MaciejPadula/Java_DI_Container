package com.github.mp.dependencyinjection.handlers;

import com.github.mp.dependencyinjection.models.enums.Lifetime;

public class SingletonHandler extends MapHandlerBase implements ISingleInstanceHandler {

    public SingletonHandler() {
        super(Lifetime.SINGLETON);
    }
}

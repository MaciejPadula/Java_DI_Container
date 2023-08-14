package com.github.mp.dependencyinjection.handlers;

import com.github.mp.dependencyinjection.models.enums.Lifetime;

public class ScopedHandler extends MapHandlerBase implements ISingleInstanceHandler {
    public ScopedHandler() {
        super(Lifetime.SCOPED);
    }
}

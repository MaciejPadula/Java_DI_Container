package com.github.mp.dependencyinjection;

import com.github.mp.dependencyinjection.exceptions.ServiceNotFoundException;

public interface IServiceProvider {
    <T> T getService(Class<T> inter);

    <T> T getRequiredService(Class<T> inter) throws ServiceNotFoundException;
}

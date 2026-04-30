package org.multithreadingAirport.factory;

public interface Factory<T, P> {
    T create(P params);
}

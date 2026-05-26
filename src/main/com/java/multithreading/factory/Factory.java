package com.java.multithreading.factory;

public interface Factory<T, P> {
    T create(P params);
}

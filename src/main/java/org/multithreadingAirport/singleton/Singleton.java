package org.multithreadingAirport.singleton;

public class Singleton {
    private Singleton() {
    }

    private static class SingletonHolder {
        static final Singleton HolderInstance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.HolderInstance;
    }
}

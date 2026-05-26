package com.java.multithreading.factory.impl;

import com.java.multithreading.entity.Airplane;
import com.java.multithreading.entity.Airport;
import com.java.multithreading.factory.Factory;

public class AirplaneFactoryImpl implements Factory<Airplane, Airport> {
    @Override
    public Airplane create(Airport airport) {
        return new Airplane(airport);
    }
}

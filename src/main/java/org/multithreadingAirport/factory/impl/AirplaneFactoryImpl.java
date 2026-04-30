package org.multithreadingAirport.factory.impl;

import org.multithreadingAirport.entityAirport.Airplane;
import org.multithreadingAirport.entityAirport.Airport;
import org.multithreadingAirport.factory.Factory;

public class AirplaneFactoryImpl implements Factory<Airplane, Airport> {
    @Override
    public Airplane create(Airport airport) {
        return new Airplane(airport);
    }
}

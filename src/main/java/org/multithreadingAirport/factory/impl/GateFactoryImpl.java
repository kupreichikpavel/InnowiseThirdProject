package org.multithreadingAirport.factory.impl;

import org.multithreadingAirport.entityAirport.Gate;
import org.multithreadingAirport.factory.Factory;

public class GateFactoryImpl implements Factory<Gate, Boolean> {
    @Override
    public Gate create(Boolean occupied) {
        return new Gate(occupied);
    }
}

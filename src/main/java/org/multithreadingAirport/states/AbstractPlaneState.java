package org.multithreadingAirport.states;

import org.multithreadingAirport.entityAirport.Airplane;
import org.multithreadingAirport.entityAirport.Airport;
import org.multithreadingAirport.exception.CustomAirPortException;

public abstract class AbstractPlaneState implements PlaneState {
    protected final Airplane airplane;
    protected final Airport airport;

    public AbstractPlaneState(Airplane airplane, Airport airport) {
        this.airplane = airplane;
        this.airport = airport;
    }

}

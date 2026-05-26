package com.java.multithreading.state;

import com.java.multithreading.entity.Airplane;
import com.java.multithreading.entity.Airport;

public abstract class AbstractPlaneState implements PlaneState {
    protected final Airplane airplane;
    protected final Airport airport;

    public AbstractPlaneState(Airplane airplane, Airport airport) {
        this.airplane = airplane;
        this.airport = airport;
    }

}

package org.multithreadingAirport.states;

import org.multithreadingAirport.exception.CustomAirPortException;

public interface PlaneState {
    void handler() throws CustomAirPortException;
}

package org.multithreadingAirport.entityAirport;

import org.multithreadingAirport.exception.CustomAirPortException;

public interface GateState {
    void handler() throws CustomAirPortException;

}

package org.multithreadingAirport.states.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.entityAirport.Airplane;
import org.multithreadingAirport.entityAirport.Airport;
import org.multithreadingAirport.exception.CustomAirPortException;
import org.multithreadingAirport.states.AbstractPlaneState;

public class ArrivedState extends AbstractPlaneState {
    private static final Logger logger = LogManager.getLogger(ArrivedState.class);

    public ArrivedState(Airplane airplane, Airport airport) {
        super(airplane, airport);
    }

    @Override
    public void handler() throws CustomAirPortException {
        logger.info(
                "Plane {} arrived",
                airplane.getPlaneId()
        );
        airplane.setPlaneState(new WaitingForGateState(airplane, airport));
    }
}

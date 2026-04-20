package org.multithreadingAirport.states.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.entityAirport.Airplane;
import org.multithreadingAirport.entityAirport.Airport;
import org.multithreadingAirport.exception.CustomAirPortException;
import org.multithreadingAirport.states.AbstractPlaneState;

public class AtGateState extends AbstractPlaneState {
    private static final Logger logger = LogManager.getLogger(AtGateState.class);

    public AtGateState(Airplane airplane, Airport airport) {
        super(airplane, airport);
    }

    @Override
    public void handler() throws CustomAirPortException {
        logger.info(
                "Plane {} occupied gate {} ",
                airplane.getPlaneId(),
                airplane.getCurrentGate().getGateId());
        airport.releaseGate(airplane);


        airplane.setPlaneState(new DepartedState(airplane, airport));
    }
}

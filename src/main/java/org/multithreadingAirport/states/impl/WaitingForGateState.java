package org.multithreadingAirport.states.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.entityAirport.Airplane;
import org.multithreadingAirport.entityAirport.Airport;
import org.multithreadingAirport.exception.CustomAirPortException;
import org.multithreadingAirport.states.AbstractPlaneState;

public class WaitingForGateState extends AbstractPlaneState {
    private static final Logger logger = LogManager.getLogger(WaitingForGateState.class);

    public WaitingForGateState(Airplane airplane, Airport airport) {
        super(airplane, airport);
    }

    @Override
    public void handler() throws CustomAirPortException {
        //TODO: переделать log и вывод информации
        logger.info("Plane " + airplane.getPlaneId() + " arrived at airport");
        airplane.setPlaneState(new AtGateState(airplane, airport));
    }
}

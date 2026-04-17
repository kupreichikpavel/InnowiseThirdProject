package org.multithreadingAirport.states.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.entityAirport.Airplane;
import org.multithreadingAirport.entityAirport.Airport;
import org.multithreadingAirport.exception.CustomAirPortException;
import org.multithreadingAirport.states.AbstractPlaneState;

public class DepartedState extends AbstractPlaneState {
    private static final Logger logger = LogManager.getLogger(DepartedState.class);

    public DepartedState(Airplane airplane, Airport airport) {
        super(airplane, airport);
    }

    @Override
    public void handler() throws CustomAirPortException {
        //TODO: переделать log и вывод информации
        logger.info("Plane " + airplane.getPlaneId() + " departed");
    }
}

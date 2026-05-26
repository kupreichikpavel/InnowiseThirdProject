package com.java.multithreading.state.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.java.multithreading.entity.Airplane;
import com.java.multithreading.entity.Airport;
import com.java.multithreading.exception.CustomAirPortException;
import com.java.multithreading.state.AbstractPlaneState;

public class WaitingForGateState extends AbstractPlaneState {
    private static final Logger logger = LogManager.getLogger(WaitingForGateState.class);

    public WaitingForGateState(Airplane airplane, Airport airport) {
        super(airplane, airport);
    }

    @Override
    public void handler() throws CustomAirPortException {
        airport.acquireGate(airplane);
        logger.info(
                "Plane {} occupied gate {} in terminal {}",
                airplane.getPlaneId(),
                airplane.getCurrentGate().getGateId(),
                airplane.getCurrentTerminal().getTerminalId()
        );
        airplane.setPlaneState(new AtGateState(airplane, airport));
    }
}

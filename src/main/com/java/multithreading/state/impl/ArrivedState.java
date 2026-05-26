package com.java.multithreading.state.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.java.multithreading.entity.Airplane;
import com.java.multithreading.entity.Airport;
import com.java.multithreading.exception.CustomAirPortException;
import com.java.multithreading.state.AbstractPlaneState;

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

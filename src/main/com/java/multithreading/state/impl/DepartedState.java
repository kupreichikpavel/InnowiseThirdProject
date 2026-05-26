package com.java.multithreading.state.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.java.multithreading.entity.Airplane;
import com.java.multithreading.entity.Airport;
import com.java.multithreading.state.AbstractPlaneState;

public class DepartedState extends AbstractPlaneState {
    private static final Logger logger = LogManager.getLogger(DepartedState.class);

    public DepartedState(Airplane airplane, Airport airport) {
        super(airplane, airport);
    }


    @Override
    public void handler() {
        logger.info("Plane {} departed", airplane.getPlaneId());
    }
}

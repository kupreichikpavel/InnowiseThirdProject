package org.multithreadingAirport.entityAirport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.exception.CustomAirPortException;

import java.util.concurrent.Callable;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import org.multithreadingAirport.states.PlaneState;
import org.multithreadingAirport.states.impl.DepartedState;
import org.multithreadingAirport.utils.IdGenerator;

@Getter
@Setter
@RequiredArgsConstructor
public class Airplane implements Callable<Void> {
    private static final Logger logger = LogManager.getLogger(Airplane.class);
    private int planeId;
    private int planeCapacity;
    private int averagePassengers;
    private int departingPassengers;
    private Gate currentGate;
    private Terminal currentTerminal;
    private PlaneState planeState;
    private Airport airport;

    public Airplane(Airport airport) {
        this.planeId = IdGenerator.nextPlaneId();
        this.airport = airport;
        logger.info("Creating Airplane with id {} ", planeId);
    }

    @Override
    public Void call() throws CustomAirPortException {
        while (!(planeState instanceof DepartedState)) {
            planeState.handler();
        }
        planeState.handler();
        return null;
    }
}
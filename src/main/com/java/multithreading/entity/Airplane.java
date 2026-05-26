package com.java.multithreading.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.java.multithreading.exception.CustomAirPortException;

import java.util.concurrent.Callable;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import com.java.multithreading.state.PlaneState;
import com.java.multithreading.util.IdGenerator;

@Getter
@Setter
@RequiredArgsConstructor
public class Airplane implements Callable<String> {
    private static final Logger logger = LogManager.getLogger(Airplane.class);
    private int planeId;
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("Airplane{");
        sb.append("planeId=").append(planeId);
        sb.append(", currentGate=").append(currentGate);
        sb.append(", currentTerminal=").append(currentTerminal);
        sb.append(", planeState=").append(planeState);
        sb.append(", airport=").append(airport);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String call() throws CustomAirPortException {
        logger.info("Airplane {} started execution", planeId);
        airport.processPlane(this);
        logger.info("Airplane {} finished execution", planeId);
        return "done";
    }
}
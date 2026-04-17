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

    public Airplane(int planeId, Airport airport) {
        this.planeId = planeId;
        this.airport = airport;
    }

    @Override
    public Void call() throws CustomAirPortException {
        while (!(planeState instanceof DepartedState)) {
            planeState.handler();
        }
        planeState.handler();
        return null;
    }


    @Override
    public final boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Airplane airplane)) return false;

        if (airplane.getPlaneId() != getPlaneId()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = getPlaneId();
        result = 31 * result + getPlaneCapacity();
        result = 31 * result + getAveragePassengers();
        result = 31 * result + currentGate.hashCode();
        result = 31 * result + getPlaneState().hashCode();
        return result;
    }
}
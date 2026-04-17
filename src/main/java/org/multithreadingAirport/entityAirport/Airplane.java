package org.multithreadingAirport.entityAirport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.exception.CustomAirPortException;

import java.util.concurrent.Callable;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class Airplane implements Callable<Void> {
    private static final Logger logger = LogManager.getLogger(Airplane.class);
    private int planeId;
    private int planeCapacity;
    private int averagePassengers;
    private Gate gate;
    private GateState planeState;


    @Override
    public Void call() throws CustomAirPortException {
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

        return getPlaneId() == airplane.getPlaneId()
                && getPlaneCapacity() == airplane.getPlaneCapacity()
                && getAveragePassengers() == airplane.getAveragePassengers()
                && getGate() == airplane.getGate()
                && getGate().equals(airplane.getGate())
                && getPlaneState().equals(airplane.getPlaneState());
    }

    @Override
    public int hashCode() {
        int result = getPlaneId();
        result = 31 * result + getPlaneCapacity();
        result = 31 * result + getAveragePassengers();
        result = 31 * result + getGate().hashCode();
        result = 31 * result + getGate().hashCode();
        result = 31 * result + getPlaneState().hashCode();
        return result;
    }
}
package org.multithreadingAirport.entityAirport;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.utils.IdGenerator;

@Getter
@Setter
@RequiredArgsConstructor
public class Gate {
    private static final Logger logger = LogManager.getLogger(Gate.class);

    private int gateId;
    private boolean occupied;

    public Gate(boolean occupied) {
        this.gateId = IdGenerator.nextGateId();
        this.occupied = occupied;
        logger.info("Creating Gate with id {}", gateId);
    }
}

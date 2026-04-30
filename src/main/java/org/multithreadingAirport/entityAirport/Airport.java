package org.multithreadingAirport.entityAirport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.exception.CustomAirPortException;
import org.multithreadingAirport.states.impl.ArrivedState;
import org.multithreadingAirport.states.impl.DepartedState;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Airport {
    private static final Logger logger = LogManager.getLogger(Airport.class);

    private final Semaphore gateSemaphore;
    private final Lock lock = new ReentrantLock();
    private final List<Terminal> terminals;
    private static List<Terminal> initTerminals;


    private Airport() {
        this.terminals = initTerminals;
        this.gateSemaphore = new Semaphore(countAllGates(terminals), true);

        logger.info("Airport instance created with {} terminals and {} gates",
                terminals.size(),
                countAllGates(terminals));
    }

    private static class AirportHolder {
        private static final Airport HOLDER_INSTANCE = new Airport();
    }

    public static void init(List<Terminal> terminals) {
        initTerminals = terminals;
        logger.info("Airport initialized with {} terminals", terminals.size());
    }

    public static Airport getInstance() {
        logger.debug("Airport instance requested");
        return AirportHolder.HOLDER_INSTANCE;
    }


    private int countAllGates(List<Terminal> terminals) {
        int count = 0;
        for (Terminal terminal : terminals) {
            count += terminal.getGates().size();
        }
        return count;
    }

    public Gate acquireGate(Airplane airplane) throws CustomAirPortException {
        try {
            gateSemaphore.acquire();
            lock.lock();

            for (Terminal terminal : terminals) {
                for (Gate gate : terminal.getGates()) {
                    if (!gate.isOccupied()) {
                        gate.setOccupied(true);
                        airplane.setCurrentTerminal(terminal);
                        airplane.setCurrentGate(gate);
                        return gate;
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CustomAirPortException("Thread interrupted while waiting for gate");
        } finally {
            lock.unlock();
        }
        logger.error("No free gate found");
        throw new CustomAirPortException("No free gate found");
    }

    public void releaseGate(Airplane airplane) {
        lock.lock();
        try {
            if (airplane.getCurrentGate() != null) {
                airplane.getCurrentGate().setOccupied(false);
                airplane.setCurrentGate(null);
                airplane.setCurrentTerminal(null);
            }
        } finally {
            lock.unlock();
        }
        gateSemaphore.release();
    }

    public void processPlane(Airplane airplane) throws CustomAirPortException {
        logger.info("Airport started processing airplane {}", airplane.getPlaneId());

        airplane.setPlaneState(new ArrivedState(airplane, this));

        while (!(airplane.getPlaneState() instanceof DepartedState)) {
            logger.debug(
                    "Airplane {} current state: {}",
                    airplane.getPlaneId(),
                    airplane.getPlaneState().getClass().getSimpleName()
            );

            airplane.getPlaneState().handler();
        }

        airplane.getPlaneState().handler();

        logger.info("Airport finished processing airplane {}", airplane.getPlaneId());
    }
}
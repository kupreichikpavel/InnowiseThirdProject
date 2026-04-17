package org.multithreadingAirport.entityAirport;

import org.multithreadingAirport.exception.CustomAirPortException;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Airport {
    private Semaphore gateSemaphore;
    private Lock lock = new ReentrantLock();
    private List<Terminal> terminals;
    //TODO: переделать log

    public Airport(List<Terminal> terminals) {
        this.terminals = terminals;
        this.gateSemaphore = new Semaphore(countAllGates(terminals), true);
        this.lock = new ReentrantLock();
    }

    private int countAllGates(List<Terminal> terminals) {
        int count = 0;
        for (Terminal terminal : terminals) {
            count += terminal.getGates().size();
        }
        return count;
    }


    public Gate acquireGate() {
        try {
            gateSemaphore.acquire();
            lock.lock();
            for (Terminal terminal : terminals) {
                for (Gate gate : terminal.getGates()) {
                    if (!gate.isOccupied()) {
                        gate.setOccupied(true);
                        return gate;
                    }
                }
            }
        } catch (
                InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
        return null;
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
}
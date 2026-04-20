package org.multithreadingAirport.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final AtomicInteger planeCounter = new AtomicInteger(1);
    private static final AtomicInteger gateCounter = new AtomicInteger(1);
    private static final AtomicInteger terminalCounter = new AtomicInteger(1);

    public static int nextPlaneId() {
        return planeCounter.getAndIncrement();
    }

    public static int nextGateId() {
        return gateCounter.getAndIncrement();
    }

    public static int nextTerminalId() {
        return terminalCounter.getAndIncrement();
    }
}
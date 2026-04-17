package org.multithreadingAirport.entityAirport;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Airport {
    private List<Gate> gateList;
    private Semaphore gateSemaphone;
    private Lock lock = new ReentrantLock();
    private int passengersInTerminal = 100;

    public Airport(List<Gate> gateList, Semaphore gateSemaphone) {
        this.gateList = gateList;
        this.gateSemaphone = new Semaphore(gateList.size(), true);
    }
}

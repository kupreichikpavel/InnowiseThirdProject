package org.multithreadingAirport.factory.impl;

import org.multithreadingAirport.entityAirport.Gate;
import org.multithreadingAirport.entityAirport.Terminal;
import org.multithreadingAirport.factory.Factory;

import java.util.ArrayList;
import java.util.List;

public class TerminalFactoryImpl implements Factory<Terminal, Integer> {
    private final GateFactoryImpl gateFactory = new GateFactoryImpl();
    @Override
    public Terminal create(Integer gateCount) {
        List<Gate> gateList = new ArrayList<>();
        for (int i = 0; i < gateCount; i++) {
            gateList.add(gateFactory.create(false));
        }
        return new Terminal(gateList);
    }
}

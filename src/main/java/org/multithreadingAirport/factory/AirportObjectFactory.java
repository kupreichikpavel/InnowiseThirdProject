package org.multithreadingAirport.factory;

import org.multithreadingAirport.entityAirport.Airplane;
import org.multithreadingAirport.entityAirport.Airport;
import org.multithreadingAirport.entityAirport.Terminal;
import org.multithreadingAirport.factory.impl.AirplaneFactoryImpl;
import org.multithreadingAirport.factory.impl.TerminalFactoryImpl;

import java.util.ArrayList;
import java.util.List;

public class AirportObjectFactory {

    private final TerminalFactoryImpl terminalFactory = new TerminalFactoryImpl();
    private final AirplaneFactoryImpl airplaneFactory = new AirplaneFactoryImpl();

    public List<Terminal> createTerminals(AirportConfig config) {
        List<Terminal> terminals = new ArrayList<>();
        int terminalCount = config.getTerminalCount();
        int gateCount = config.getGateCount();
        int baseGateCount = gateCount / terminalCount;

        for (int i = 0; i < terminalCount; i++) {
            int gatesForCurrentTerminal = baseGateCount;
            Terminal terminal = terminalFactory.create(gatesForCurrentTerminal);
            terminals.add(terminal);
        }

        return terminals;
    }

    public List<Airplane> createAirplanes(AirportConfig config, Airport airport) {
        List<Airplane> airplanes = new ArrayList<>();

        for (int i = 0; i < config.getAirpalneCount(); i++) {
            airplanes.add(airplaneFactory.create(airport));
        }
        return airplanes;
    }
}

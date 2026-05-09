package org.multithreadingAirport.entityAirport;

import org.junit.jupiter.api.Test;
import org.multithreadingAirport.exception.CustomAirPortException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    @Test
    void getInstanceShouldReturnSameAirport() {
        Airport.init(List.of(new Terminal(List.of(new Gate(false)))));

        Airport first = Airport.getInstance();
        Airport second = Airport.getInstance();

        assertSame(first, second);
    }

    @Test
    void acquireGateShouldOccupyFreeGateAndSetAirplaneFields() throws CustomAirPortException {
        Gate gate = new Gate(false);
        Terminal terminal = new Terminal(List.of(gate));

        Airport.init(List.of(terminal));
        Airport airport = Airport.getInstance();

        Airplane airplane = new Airplane();

        Gate result = airport.acquireGate(airplane);

        assertSame(gate, result);
        assertTrue(gate.isOccupied());
        assertSame(gate, airplane.getCurrentGate());
        assertSame(terminal, airplane.getCurrentTerminal());

        airport.releaseGate(airplane);
    }

    @Test
    void releaseGateShouldFreeGateAndClearAirplaneFields() throws CustomAirPortException {
        Gate gate = new Gate(false);
        Terminal terminal = new Terminal(List.of(gate));

        Airport.init(List.of(terminal));
        Airport airport = Airport.getInstance();

        Airplane airplane = new Airplane();

        airport.acquireGate(airplane);
        airport.releaseGate(airplane);

        assertFalse(gate.isOccupied());
        assertNull(airplane.getCurrentGate());
        assertNull(airplane.getCurrentTerminal());
    }
}
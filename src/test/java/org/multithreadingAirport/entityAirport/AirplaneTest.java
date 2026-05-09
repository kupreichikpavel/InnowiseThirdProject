package org.multithreadingAirport.entityAirport;

import org.junit.jupiter.api.Test;
import org.multithreadingAirport.exception.CustomAirPortException;
import org.multithreadingAirport.states.PlaneState;

import static org.junit.jupiter.api.Assertions.*;

class AirplaneTest {

    @Test
    void settersShouldChangeFields() {
        Airplane airplane = new Airplane();
        Gate gate = new Gate(false);
        Terminal terminal = new Terminal();
        PlaneState state = new TestPlaneState();

        airplane.setPlaneId(10);
        airplane.setCurrentGate(gate);
        airplane.setCurrentTerminal(terminal);
        airplane.setPlaneState(state);

        assertEquals(10, airplane.getPlaneId());
        assertSame(gate, airplane.getCurrentGate());
        assertSame(terminal, airplane.getCurrentTerminal());
        assertSame(state, airplane.getPlaneState());
    }

    @Test
    void constructorWithAirportShouldSetAirportAndGeneratePlaneId() {
        Airport.init(java.util.List.of(new Terminal(java.util.List.of(new Gate(false)))));
        Airport airport = Airport.getInstance();

        Airplane airplane = new Airplane(airport);

        assertSame(airport, airplane.getAirport());
        assertTrue(airplane.getPlaneId() > 0);
    }

    @Test
    void toStringShouldContainMainFields() {
        Airplane airplane = new Airplane();
        airplane.setPlaneId(5);

        String result = airplane.toString();

        assertTrue(result.contains("planeId=5"));
        assertTrue(result.contains("currentGate"));
        assertTrue(result.contains("currentTerminal"));
        assertTrue(result.contains("planeState"));
        assertTrue(result.contains("airport"));
    }

    private static class TestPlaneState implements PlaneState {
        @Override
        public void handler() throws CustomAirPortException {
        }
    }
}
package org.multithreadingAirport.entityAirport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GateTest {

    @Test
    void constructorShouldSetOccupiedAndGenerateId() {
        Gate gate = new Gate(true);

        assertTrue(gate.isOccupied());
        assertTrue(gate.getGateId() > 0);
    }

    @Test
    void settersShouldChangeFields() {
        Gate gate = new Gate(false);

        gate.setGateId(100);
        gate.setOccupied(true);

        assertEquals(100, gate.getGateId());
        assertTrue(gate.isOccupied());
    }
}
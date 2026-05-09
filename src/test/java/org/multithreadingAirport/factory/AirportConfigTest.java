package org.multithreadingAirport.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportConfigTest {

    @Test
    void constructorShouldSetAllCounts() {
        AirportConfig config = new AirportConfig(2, 4, 6);

        assertEquals(2, config.getTerminalCount());
        assertEquals(4, config.getAirpalneCount());
        assertEquals(6, config.getGateCount());
    }

    @Test
    void settersShouldChangeCounts() {
        AirportConfig config = new AirportConfig(1, 1, 1);

        config.setTerminalCount(3);
        config.setAirpalneCount(5);
        config.setGateCount(7);

        assertEquals(3, config.getTerminalCount());
        assertEquals(5, config.getAirpalneCount());
        assertEquals(7, config.getGateCount());
    }

    @Test
    void toStringShouldContainAllCounts() {
        AirportConfig config = new AirportConfig(2, 4, 6);

        String result = config.toString();

        assertTrue(result.contains("terminalCount=2"));
        assertTrue(result.contains("airpalnesCount=4"));
        assertTrue(result.contains("gatesCount=6"));
    }
}
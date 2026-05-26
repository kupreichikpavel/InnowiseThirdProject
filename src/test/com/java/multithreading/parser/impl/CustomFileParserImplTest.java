package com.java.multithreading.parser.impl;

import org.junit.jupiter.api.Test;
import com.java.multithreading.factory.AirportConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileParserImplTest {

    @Test
    void parseShouldCreateAirportConfigFromLines() {
        CustomFileParserImpl parser = new CustomFileParserImpl();

        AirportConfig config = parser.parse(List.of(
                "terminals=2",
                "gates=5",
                "airplanes=7"
        ));

        assertEquals(2, config.getTerminalCount());
        assertEquals(5, config.getGateCount());
        assertEquals(7, config.getAirpalneCount());
        assertSame(config, parser.getConfig());
    }

    @Test
    void parseShouldIgnoreUnknownKeys() {
        CustomFileParserImpl parser = new CustomFileParserImpl();

        AirportConfig config = parser.parse(List.of(
                "terminals=1",
                "unknown=100",
                "gates=2",
                "airplanes=3"
        ));

        assertEquals(1, config.getTerminalCount());
        assertEquals(2, config.getGateCount());
        assertEquals(3, config.getAirpalneCount());
    }

    @Test
    void parseShouldThrowWhenLineHasInvalidNumber() {
        CustomFileParserImpl parser = new CustomFileParserImpl();

        assertThrows(NumberFormatException.class, () -> parser.parse(List.of("terminals=abc")));
    }
}
package org.multithreadingAirport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MainTest {

    @Test
    void mainShouldNotThrowInCurrentImplementation() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
package com.java.multithreading.entity;


import com.java.multithreading.entity.Gate;
import com.java.multithreading.entity.Terminal;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TerminalTest {

    @Test
    void constructorShouldSetGatesAndGenerateId() {
        Gate gate = new Gate(false);

        Terminal terminal = new Terminal(List.of(gate));

        assertTrue(terminal.getTerminalId() > 0);
        assertEquals(List.of(gate), terminal.getGates());
    }

    @Test
    void settersShouldChangeFields() {
        Terminal terminal = new Terminal();

        terminal.setTerminalId(10);
        terminal.setGates(List.of(new Gate(false)));

        assertEquals(10, terminal.getTerminalId());
        assertEquals(1, terminal.getGates().size());
    }
}
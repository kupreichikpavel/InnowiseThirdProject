package org.multithreadingAirport.customFileReader.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.multithreadingAirport.exception.CustomAirPortException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderImplTest {

    private final CustomFileReaderImpl reader = new CustomFileReaderImpl();

    @TempDir
    Path tempDir;

    @Test
    void readShouldReturnOnlyNotEmptyLines() throws Exception {
        Path file = tempDir.resolve("airport.txt");
        Files.write(file, List.of("terminals=2", "", "gates=3", "airplanes=4"));

        List result = reader.read(file.toString());

        assertEquals(List.of("terminals=2", "gates=3", "airplanes=4"), result);
    }

    @Test
    void readShouldThrowWhenPathIsNull() {
        CustomAirPortException exception = assertThrows(
                CustomAirPortException.class,
                () -> reader.read(null)
        );

        assertEquals("Error path to file, path is null", exception.getMessage());
    }

    @Test
    void readShouldThrowWhenPathIsEmpty() {
        CustomAirPortException exception = assertThrows(
                CustomAirPortException.class,
                () -> reader.read("")
        );

        assertEquals("Error path to file, path is empty", exception.getMessage());
    }

    @Test
    void readShouldThrowWhenFileDoesNotExist() {
        assertThrows(
                CustomAirPortException.class,
                () -> reader.read(tempDir.resolve("missing.txt").toString())
        );
    }

    @Test
    void readShouldThrowWhenFileHasNoNotEmptyLines() throws Exception {
        Path file = tempDir.resolve("empty.txt");
        Files.write(file, List.of("", ""));

        assertThrows(CustomAirPortException.class, () -> reader.read(file.toString()));
    }
}
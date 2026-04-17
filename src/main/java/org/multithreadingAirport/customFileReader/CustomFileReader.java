package org.multithreadingAirport.customFileReader;

import org.multithreadingAirport.exception.CustomAirPortException;

import java.util.List;

public interface CustomFileReader {
    List<String> read(String path) throws CustomAirPortException;
}

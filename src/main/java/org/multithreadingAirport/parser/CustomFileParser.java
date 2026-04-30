package org.multithreadingAirport.parser;

import org.multithreadingAirport.factory.AirportConfig;

import java.util.List;

public interface CustomFileParser {
    AirportConfig parse(List<String> file);
}

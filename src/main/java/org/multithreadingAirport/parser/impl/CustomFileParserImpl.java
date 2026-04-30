package org.multithreadingAirport.parser.impl;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.factory.AirportConfig;
import org.multithreadingAirport.parser.CustomFileParser;

import java.util.List;

@Getter
public class CustomFileParserImpl implements CustomFileParser {
    private static final Logger logger = LogManager.getLogger(CustomFileParserImpl.class);
    private AirportConfig config;

    @Override
    public AirportConfig parse(List<String> file) {

        logger.info("Parsing data from file");
        int terminals = 0;
        int gates = 0;
        int airplanes = 0;
        for (String line : file) {
            String[] parts = line.split("=");
            String key = parts[0].trim();
            int value = Integer.parseInt(parts[1].trim());
            switch (key) {
                case "terminals" -> terminals = value;
                case "gates" -> gates = value;
                case "airplanes" -> airplanes = value;
            }
        }
        return config = new AirportConfig(terminals, airplanes, gates);
    }
}

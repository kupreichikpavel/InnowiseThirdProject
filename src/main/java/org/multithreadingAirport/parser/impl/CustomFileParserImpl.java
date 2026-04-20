package org.multithreadingAirport.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.parser.CustomFileParser;

import java.util.List;

public class CustomFileParserImpl implements CustomFileParser {
    private static final Logger logger = LogManager.getLogger(CustomFileParserImpl.class);

    @Override
    public void parse(List<String> file) {
        logger.info("Parsing data from file");
    }
    //TODO: сделать работy parser из файла
}

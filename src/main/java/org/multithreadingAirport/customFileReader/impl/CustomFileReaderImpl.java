package org.multithreadingAirport.customFileReader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.customFileReader.CustomFileReader;
import org.multithreadingAirport.exception.CustomAirPortException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;
import static java.nio.file.Path.of;

public class CustomFileReaderImpl implements CustomFileReader {

    private static final Logger logger = LogManager.getLogger(CustomFileReaderImpl.class);


    @Override
    public List<String> read(String path) throws CustomAirPortException {
        if (path == null) {
            logger.warn("Error path to file, path is null");
            throw new CustomAirPortException("Error path to file, path is null");
        } else if (path.isEmpty()) {
            logger.warn("Error path to file, path is empty");
            throw new CustomAirPortException("Error path to file, path is empty");
        }
        try (Stream<String> lines = lines(of(path))) {
            logger.info("Starting reading info from file");
            List<String> resultLines = lines
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());
            if (resultLines.isEmpty()) {
                throw new CustomAirPortException();
            }
            return resultLines;
        } catch (Exception e) {
            throw new CustomAirPortException();
        }
    }
}

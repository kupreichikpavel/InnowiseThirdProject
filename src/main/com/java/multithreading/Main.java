package com.java.multithreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.java.multithreading.reader.CustomFileReader;
import com.java.multithreading.reader.impl.CustomFileReaderImpl;
import com.java.multithreading.entity.Airplane;
import com.java.multithreading.entity.Airport;
import com.java.multithreading.entity.Terminal;
import com.java.multithreading.exception.CustomAirPortException;
import com.java.multithreading.factory.AirportConfig;
import com.java.multithreading.factory.AirportObjectFactory;
import com.java.multithreading.parser.CustomFileParser;
import com.java.multithreading.parser.impl.CustomFileParserImpl;

import java.util.List;
import java.util.concurrent.*;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ExecutorService executorService = null;

        try {
            logger.info("Application started");

            CustomFileReader reader = new CustomFileReaderImpl();
            CustomFileParser parser = new CustomFileParserImpl();
            AirportConfig config = parser.parse(reader.read("/Users/alexey/IdeaProjects/InnowiseThirdProject/src/main/resources/file.txt"));

            logger.info(
                    "Config parsed: terminals={}, gates={}, airplanes={}",
                    config.getTerminalCount(),
                    config.getGateCount(),
                    config.getAirpalneCount()
            );

            AirportObjectFactory airportObjectFactory = new AirportObjectFactory();

            List<Terminal> terminals = airportObjectFactory.createTerminals(config);
            logger.info("Terminals and gates created");

            Airport.init(terminals);
            Airport airport = Airport.getInstance();

            logger.info("Airport singleton initialized");

            List<Airplane> airplanes = airportObjectFactory.createAirplanes(config, airport);
            logger.info("Airplanes created: count={}", airplanes.size());

            executorService = Executors.newFixedThreadPool(config.getAirpalneCount());

            for (Airplane airplane : airplanes) {
                executorService.submit(airplane);
                logger.debug("Airplane {} submitted to executor", airplane.getPlaneId());
            }

            executorService.shutdown();

            boolean finished = executorService.awaitTermination(1, TimeUnit.MINUTES);

            if (finished) {
                logger.info("All airplanes finished work");
            } else {
                logger.warn("Not all airplanes finished in time");
                executorService.shutdownNow();
            }

            logger.info("Application finished");


        } catch (CustomAirPortException e) {
            logger.error("Airport error occurred", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Main thread was interrupted", e);
            executorService.shutdownNow();
        }
    }
}


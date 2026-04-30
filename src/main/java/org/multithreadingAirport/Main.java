package org.multithreadingAirport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.customFileReader.CustomFileReader;
import org.multithreadingAirport.customFileReader.impl.CustomFileReaderImpl;
import org.multithreadingAirport.entityAirport.Airplane;
import org.multithreadingAirport.entityAirport.Airport;
import org.multithreadingAirport.entityAirport.Terminal;
import org.multithreadingAirport.exception.CustomAirPortException;
import org.multithreadingAirport.factory.AirportConfig;
import org.multithreadingAirport.factory.AirportObjectFactory;
import org.multithreadingAirport.parser.CustomFileParser;
import org.multithreadingAirport.parser.impl.CustomFileParserImpl;

import java.util.ArrayList;
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


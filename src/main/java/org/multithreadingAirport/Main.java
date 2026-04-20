package org.multithreadingAirport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.entityAirport.Airplane;
import org.multithreadingAirport.entityAirport.Airport;
import org.multithreadingAirport.entityAirport.Gate;
import org.multithreadingAirport.entityAirport.Terminal;
import org.multithreadingAirport.exception.CustomAirPortException;
import org.multithreadingAirport.states.impl.ArrivedState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws CustomAirPortException, ExecutionException, InterruptedException {
        List<Gate> terminal1Gates = List.of(
                new Gate(false),
                new Gate(false)
        );

        List<Gate> terminal2Gates = List.of(
                new Gate(false),
                new Gate(false)
        );

        Terminal terminal1 = new Terminal(terminal1Gates);
        Terminal terminal2 = new Terminal(terminal2Gates);

        Airport airport = new Airport(List.of(terminal1, terminal2));

        List<Airplane> airplanes = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Airplane airplane = new Airplane(airport);
            airplane.setPlaneState(new ArrivedState(airplane, airport));
            airplanes.add(airplane);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Void>> futures = executorService.invokeAll(airplanes);

        for (Future<Void> future : futures) {
            future.get();
        }

        executorService.shutdown();
    }
}


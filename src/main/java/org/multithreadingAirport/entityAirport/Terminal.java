package org.multithreadingAirport.entityAirport;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multithreadingAirport.utils.IdGenerator;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Terminal {
    private static final Logger logger = LogManager.getLogger(Terminal.class);
    private int terminalId;
    private List<Gate> gates;

    public Terminal(List<Gate> gates) {
        this.terminalId = IdGenerator.nextTerminalId();
        this.gates = gates;
        logger.info("Creating Terminal with id {}", terminalId);
    }
}

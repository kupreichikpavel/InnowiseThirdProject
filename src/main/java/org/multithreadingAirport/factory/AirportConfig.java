package org.multithreadingAirport.factory;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
@Setter
public class AirportConfig {
    private static final Logger logger = LogManager.getLogger(AirportConfig.class);
    private int terminalCount;
    private int airpalneCount;
    private int gateCount;

    public AirportConfig(int terminalCount, int airpalnesCount, int gatesCount) {
        logger.info("Create AirportConfig from parser");
        this.terminalCount = terminalCount;
        this.airpalneCount = airpalnesCount;
        this.gateCount = gatesCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AirportConfig{");
        sb.append("terminalCount=").append(terminalCount);
        sb.append(", airpalnesCount=").append(airpalneCount);
        sb.append(", gatesCount=").append(gateCount);
        sb.append('}');
        return sb.toString();
    }
}

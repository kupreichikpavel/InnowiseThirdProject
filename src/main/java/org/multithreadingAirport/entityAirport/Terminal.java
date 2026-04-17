package org.multithreadingAirport.entityAirport;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
//TODO: переделать log и сделать работу с id
public class Terminal {
    private int terminalId;
    private List<Gate> gates;

    public Terminal(int terminalId, List<Gate> gates) {
        this.terminalId = terminalId;
        this.gates = gates;
    }
}

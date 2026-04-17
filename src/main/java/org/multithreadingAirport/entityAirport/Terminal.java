package org.multithreadingAirport.entityAirport;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Terminal {
    private int capacity;
    private int currentPassengers;
}

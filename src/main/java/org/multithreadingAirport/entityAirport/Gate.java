package org.multithreadingAirport.entityAirport;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
//TODO: переделать log и сделать работу с id
public class Gate {
    private int id;
    private int size;
    private boolean occupied;

    public Gate(int id, boolean occupied) {
        this.id = id;
        this.occupied = occupied;
    }


}

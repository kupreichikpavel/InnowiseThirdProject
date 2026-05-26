package com.java.multithreading.state;

import com.java.multithreading.exception.CustomAirPortException;

public interface PlaneState {
    void handler() throws CustomAirPortException;
}

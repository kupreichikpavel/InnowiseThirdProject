package com.java.multithreading.factory.impl;

import com.java.multithreading.entity.Gate;
import com.java.multithreading.factory.Factory;

public class GateFactoryImpl implements Factory<Gate, Boolean> {
    @Override
    public Gate create(Boolean occupied) {
        return new Gate(occupied);
    }
}

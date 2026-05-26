package com.java.multithreading.reader;

import com.java.multithreading.exception.CustomAirPortException;

import java.util.List;

public interface CustomFileReader {
    List<String> read(String path) throws CustomAirPortException;
}

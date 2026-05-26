package com.java.multithreading.parser;

import com.java.multithreading.factory.AirportConfig;

import java.util.List;

public interface CustomFileParser {
    AirportConfig parse(List<String> file);
}

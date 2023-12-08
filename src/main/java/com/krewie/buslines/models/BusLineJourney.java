package com.krewie.buslines.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BusLineJourney {
    private String lineNumber;
    private Integer nrOfStops;
    private List<BusStops> busStops;
}

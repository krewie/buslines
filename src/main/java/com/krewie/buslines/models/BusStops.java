package com.krewie.buslines.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class BusStops {
    private String lineNumber;
    private String stopPointNumber;
    private String stopPointName;
}

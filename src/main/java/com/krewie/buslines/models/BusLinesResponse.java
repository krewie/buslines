package com.krewie.buslines.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BusLinesResponse {
    private List<BusLineJourney> busLineJourneys;
}

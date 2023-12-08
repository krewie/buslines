package com.krewie.buslines.services;

import com.krewie.buslines.models.BusLinesResponse;
import com.krewie.buslines.models.BusStops;
import com.krewie.buslines.models.BusLineJourney;
import com.krewie.buslines.models.external.JourneyResponse;
import com.krewie.buslines.models.external.StopPoint;
import com.krewie.buslines.models.external.StopPointResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BusService {

    @Autowired
    private SLApiService slApiService;


    public BusService(SLApiService slApiService) {
        this.slApiService = slApiService;
    }

    public List<List<BusStops>> sortAndOrderLines(JourneyResponse journeyResponse, StopPointResponse stopPointResponse, int limit) {

        //sort stopPoints by stopPointNumber, use it as lookup later
        Map<String, List<StopPoint>> stopPointMap =
                stopPointResponse.getResponseData()
                        .getResult()
                        .stream()
                        .collect(Collectors.groupingBy(StopPoint::getStopPointNumber));

        //filter out the direction, map to BusStops and group by line number
        Map<String, List<BusStops>> journeyMap =
                journeyResponse.getResponseData()
                        .getResult().stream()
                        .filter(journeyPatternOnLine ->
                                //Only interested in one direction
                                journeyPatternOnLine.getDirectionCode().equals("1"))
                        .map(journeyPatternOnLine -> {
                            //in case of data errors, return a default value
                            if (stopPointMap.get(journeyPatternOnLine.getJourneyPatternPointNumber()) == null ||
                                    stopPointMap.get(journeyPatternOnLine.getJourneyPatternPointNumber()).isEmpty()) {
                        return new BusStops(
                                journeyPatternOnLine.getLineNumber(),
                                journeyPatternOnLine.getJourneyPatternPointNumber(),
                                "No name found");
                    } else {
                        return new BusStops(
                                journeyPatternOnLine.getLineNumber(),
                                journeyPatternOnLine.getJourneyPatternPointNumber(),
                                stopPointMap.get(journeyPatternOnLine.getJourneyPatternPointNumber()).get(0)
                                        .getStopPointName());
                    }
                }).collect(Collectors.groupingBy(BusStops::getLineNumber));

        //for values in map, Sort by size of the values list by descending order
        return journeyMap.values().stream()
                .sorted((o1, o2) -> o2.size() - o1.size())
                .limit(limit)
                .toList();
    }

    public BusLinesResponse getSortedStops(int limit) {

        List<List<BusStops>> stops = slApiService.getJourneyAlongLines()
                .zipWith(slApiService.getStopPoints())
                .flatMap(tuple -> Mono.just(sortAndOrderLines(tuple.getT1(), tuple.getT2(), limit)))
                .block();

        assert stops != null;

        return new BusLinesResponse(stops.stream()
                .map(entry -> new BusLineJourney(entry.get(0).getLineNumber(), entry.size(), entry))
                .toList());
    }
}

package com.krewie.buslines.controller;

import com.krewie.buslines.models.BusLinesResponse;
import com.krewie.buslines.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/bus-stops")
public class ApiController {
    private BusService busService;

    @Autowired
    public ApiController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("/top")
    @ResponseBody
    public ResponseEntity<BusLinesResponse> getBusStopsDesc() throws IOException {
        try {
            int limit = 10; // top ten
            BusLinesResponse sortedStops = busService.getSortedStops(limit);
            if (sortedStops == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(sortedStops, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

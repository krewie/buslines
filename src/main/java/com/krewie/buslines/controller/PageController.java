package com.krewie.buslines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    @GetMapping("/bus-lines")
    public ModelAndView getBusStops() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bus-stops.html");
        return modelAndView;
    }
}

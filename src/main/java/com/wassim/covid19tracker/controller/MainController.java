package com.wassim.covid19tracker.controller;


import com.wassim.covid19tracker.ServiceCovidData;
import com.wassim.covid19tracker.model.Cordinations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class MainController {

    @Autowired
    ServiceCovidData serviceCovidData ;

    @GetMapping("/")

    public  String homePage(Model model){
        ArrayList <Cordinations> allStats = serviceCovidData.getCordinationsList();

        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getNumberOfCases()).sum();

        model.addAttribute("allStats",allStats);

        model.addAttribute("totalnum",totalReportedCases);


        return "home";

    }
}

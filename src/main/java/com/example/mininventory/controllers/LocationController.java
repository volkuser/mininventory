package com.example.mininventory.controllers;

import com.example.mininventory.models.Location;
import com.example.mininventory.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Controller
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/location")
    public String show(Model model) {
        loadList(model);
        return "location_control";
    }

    @GetMapping("/location/exact")
    public String exactSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Location> locations = locationService.getResultExactSearch(query);

            model.addAttribute("locations", locations);
            return "location_control";
        } else return "redirect:/location";
    }

    @GetMapping("/location/imprecise")
    public String impreciseSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Location> locations = locationService.getResultImpreciseSearch(query);

            model.addAttribute("locations", locations);
            return "location_control";
        } else return "redirect:/location";
    }

    @PostMapping("/location")
    public String add(@RequestParam(value = "number" ) String numberAsString,
                         @RequestParam(value = "additionLetter", required = false)
                         String additionLetterAsString,
                         @RequestParam(value = "isAuditory", required = false) String isAuditoryAsString,
                         @RequestParam(value = "operatingHours") String operatingHoursAsString,
                         @RequestParam(value = "openFrom") String openFromAsString, Model model) {
        locationService.addFromView(numberAsString, additionLetterAsString, isAuditoryAsString,
                operatingHoursAsString, openFromAsString);

        loadList(model);
        return "location_control";
    }

    private void loadList(Model model){
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        model.addAttribute("locations", locations);
    }
}

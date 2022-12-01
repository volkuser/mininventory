package com.example.mininventory.controllers;

import com.example.mininventory.models.Location;
import com.example.mininventory.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;
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

    private void loadList(Model model){
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        model.addAttribute("locations", locations);
        model.addAttribute("selectedLocation", new Location());
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
    public String add(@Valid @ModelAttribute("selectedLocation") Location location, BindingResult bindingResult,
                      @RequestParam(value = "operatingHoursAsString") String operatingHoursAsString,
                      @RequestParam(value = "isAuditoryAsString", defaultValue = "off") String isAuditoryAsString,
                      Model model) {
        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            location.setAuditory(isAuditoryAsString.equals("on"));
            try {
                location.setOperatingHours(Time.valueOf(operatingHoursAsString + ":00"));
            } catch (Exception ignored) {
                try {
                    location.setOperatingHours(locationService.getById(location.getId()).getOperatingHours());
                } catch (Exception ignored1) {}
            }
            locationService.save(location);
        }

        loadList(model);
        return "location_control";
    }
}

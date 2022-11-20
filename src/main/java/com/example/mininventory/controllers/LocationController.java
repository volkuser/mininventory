package com.example.mininventory.controllers;

import com.example.mininventory.models.Location;
import com.example.mininventory.repos.LocationRepository;
import com.example.mininventory.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Controller
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/location")
    public String filter(/*@RequestParam(required = false) String filter, */Model model) {
        //Iterable<Location> locations = locationRepository.findAll();
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        /*if (filter != null && !filter.isEmpty()) locations = locationRepository.findAll();
        else locationRepository.findAll();*/

        model.addAttribute("locations", locations);
        // model.addAttribute("filter", filter);

        return "location_control";
    }

    @PostMapping("/location")
    public String adding(@RequestParam(value = "number" ) String numberAsString,
                         @RequestParam(value = "additionLetter", required = false)
                         String additionLetterAsString,
                         @RequestParam(value = "isAuditory", required = false) String isAuditoryAsString,
                         @RequestParam(value = "operatingHours") String operatingHoursAsString,
                         @RequestParam(value = "openFrom") String openFromAsString, Map<String, Object> model) {
        byte number = Byte.parseByte(numberAsString);
        char additionLetter = (additionLetterAsString.isEmpty()) ? 'x' : additionLetterAsString.charAt(0);
        boolean isAuditory = isAuditoryAsString != null;
        Time operatingHours = Time.valueOf(operatingHoursAsString + ":00");
        Date openFrom = Date.valueOf(openFromAsString);
        Location location = new Location(number, additionLetter, isAuditory, operatingHours, openFrom);

        //Location location = new Location();
        locationService.add(location);

        /*Iterable<Location> locations = locationRepository.findAll();
        model.put("locations", locations);*/
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        model.put("locations", locations);

        return "location_control";
    }
}

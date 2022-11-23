package com.example.mininventory.controllers;

import com.example.mininventory.models.Location;
import com.example.mininventory.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LocationItemController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/location/more/{id}")
    public String more(@PathVariable("id") String id, Model model){

        getAndLoadLocation(model, id);

        return "location_item_control";
    }

    @PostMapping("/location/more/{id}")
    public String update(@PathVariable("id") String id, @RequestParam(value = "number" ) String number,
                         @RequestParam(value = "additionLetter", required = false) String additionLetter,
                         @RequestParam(value = "isAuditory", required = false) String isAuditory,
                         @RequestParam(value = "operatingHours") String operatingHours,
                         @RequestParam(value = "openFrom") String openFrom, Model model){
        locationService.updateFromView(id, number, additionLetter, isAuditory, operatingHours, openFrom);

        getAndLoadLocation(model, id);

        return "location_item_control";
    }

    private void getAndLoadLocation(Model model, String id){
        Location location = locationService.getById(Long.parseLong(id));
        model.addAttribute("selectedLocation", location);
        // for date
        model.addAttribute("openFrom", location.getOpenFrom().toString());
    }
}

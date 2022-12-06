package com.example.mininventory.controllers;

import com.example.mininventory.models.Location;
import com.example.mininventory.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;
import java.util.Map;

@Controller
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
public class LocationItemController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/location/more/{id}")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "location_item_control";
    }

    private void getAndLoad(Model model, String id){
        Location location = locationService.getById(Long.parseLong(id));
        model.addAttribute("selectedLocation", location);
    }

    @PostMapping("/location/more/{id}")
    public String update(@Valid @ModelAttribute("selectedLocation") Location location, BindingResult bindingResult,
                         Model model, // specific is down
                         @RequestParam(value = "operatingHoursAsString") String operatingHoursAsString,
                         @RequestParam(value = "isAuditoryAsString", defaultValue = "off") String isAuditoryAsString){
        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            // specific
            location.setAuditory(isAuditoryAsString.equals("on"));
            try {
                location.setOperatingHours(Time.valueOf(operatingHoursAsString + ":00"));
            } catch (Exception ignored) {
                location.setOperatingHours(locationService.getById(location.getId()).getOperatingHours());
            }
            locationService.save(location);
        }

        getAndLoad(model, location.getId().toString());
        return "location_item_control";
    }

    @PostMapping("/location/more/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model){
        try{
            locationService.deleteById(Long.parseLong(id));
            return "redirect:/location";
        } catch (Exception exception) {
            getAndLoad(model, id);
            return "redirect:/location/more/{id}";
        }
    }
}

package com.example.mininventory.controllers;

import com.example.mininventory.models.Location;
import com.example.mininventory.services.LocationService;
import net.bytebuddy.dynamic.scaffold.FieldRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Time;
import java.util.Map;

@Controller
public class LocationItemController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/location/more/{id}")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoadLocation(model, id);
        return "location_item_control";
    }

    private void getAndLoadLocation(Model model, String id){
        Location location = locationService.getById(Long.parseLong(id));
        model.addAttribute("selectedLocation", location);
        // specific
        //model.addAttribute("operatingHours", location.getOperatingHours().toString());
        model.addAttribute("openFrom", location.getOpenFrom().toString());
    }

    /*@PostMapping("/location/more/{id}")
    public String update(@PathVariable("id") String id, @RequestParam(value = "number" ) String number,
                         @RequestParam(value = "additionLetter", required = false) String additionLetter,
                         @RequestParam(value = "isAuditory", required = false) String isAuditory,
                         @RequestParam(value = "operatingHours") String operatingHours,
                         @RequestParam(value = "openFrom") String openFrom, Model model){
        locationService.updateFromView(id, number, additionLetter, isAuditory, operatingHours, openFrom);

        getAndLoadLocation(model, id);
        return "location_item_control";
    }*/

    @PostMapping("/location/more/{id}")
    public String update(@Valid @ModelAttribute("selectedLocation") Location location, BindingResult bindingResult,
                         /*@RequestParam(value = "openFrom") Date openFromAsString,*/
                         @RequestParam(value = "operatingHoursAsString") String operatingHoursAsString,
                         @RequestParam(value = "isAuditoryAsString", defaultValue = "off") String isAuditoryAsString,
                         Model model){
        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            location.setAuditory(isAuditoryAsString.equals("on"));
            try {
                location.setOperatingHours(Time.valueOf(operatingHoursAsString + ":00"));
            } catch (Exception ignored) {
                location.setOperatingHours(locationService.getById(location.getId()).getOperatingHours());
            }
            locationService.update(location);
        }

        getAndLoadLocation(model, location.getId().toString());
        return "location_item_control";
    }

    @PostMapping("/location/more/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model){
        try{
            locationService.deleteById(Long.parseLong(id));
            return "redirect:/location";
        } catch (Exception exception) {
            getAndLoadLocation(model, id);
            return "redirect:/location/more/{id}";
        }
    }
}

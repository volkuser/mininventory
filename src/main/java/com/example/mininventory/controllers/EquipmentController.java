package com.example.mininventory.controllers;

import com.example.mininventory.models.Equipment;
import com.example.mininventory.models.Location;
import com.example.mininventory.services.EquipmentService;
import com.example.mininventory.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Controller
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private LocationService locationService;

    @GetMapping("/equipment")
    public String show(Model model) {
        List<Equipment> equipments = StreamSupport.stream(equipmentService.getAll().spliterator(),
                false).toList();

        // select of locations
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        model.addAttribute("locations", locations);

        model.addAttribute("equipments", equipments);

        return "equipment_control";
    }

    @GetMapping("/equipment/exact")
    public String exactSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Equipment> equipments = equipmentService.getResultExactSearch(query);

            // select of locations
            List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                    false).toList();
            model.addAttribute("locations", locations);

            model.addAttribute("equipments", equipments);

            return "equipment_control";
        } else return "redirect:/equipment";
    }

    @GetMapping("/equipment/imprecise")
    public String impreciseSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Equipment> equipments = equipmentService.getResultImpreciseSearch(query);

            // select of locations
            List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                    false).toList();
            model.addAttribute("locations", locations);

            model.addAttribute("equipments", equipments);

            return "equipment_control";
        } else return "redirect:/equipment";
    }

    @PostMapping("/equipment")
    public String adding(@RequestParam(value = "inventoryNumber") String inventoryNumber,
                         @RequestParam(value = "weight") String weightAsString,
                         @RequestParam(value = "yearOfEntry") String yearOfEntryAsString,
                         @RequestParam(value = "count") String countAsString,
                         @RequestParam(value = "location") String locationAsString, Map<String, Object> model){
        equipmentService.addFromView(inventoryNumber, weightAsString, yearOfEntryAsString, countAsString,
                locationAsString);

        List<Equipment> equipments = StreamSupport.stream(equipmentService.getAll().spliterator(),
                false).toList();

        // select of locations
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        model.put("locations", locations);

        model.put("equipments", equipments);

        return "equipment_control";
    }
}

package com.example.mininventory.controllers;

import com.example.mininventory.models.Equipment;
import com.example.mininventory.models.Location;
import com.example.mininventory.services.EquipmentService;
import com.example.mininventory.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
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
        // join
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();

        model.addAttribute("equipments", equipments);
        model.addAttribute("locations", locations);

        return "equipment_control";
    }
}

package com.example.mininventory.controllers;

import com.example.mininventory.models.Equipment;
import com.example.mininventory.services.EquipmentService;
import com.example.mininventory.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@Controller
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
public class EquipmentListOnLocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/location/more/{id}/list")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "location_equipment_list";
    }

    private void getAndLoad(Model model, String id){
        model.addAttribute("actualId", id);
        // main list
        List<Equipment> equipments = equipmentService.getByLocationId(id);
        model.addAttribute("equipmentsOnLocation", equipments);
        // select with other items
        List<Equipment> otherEquipments = StreamSupport.stream(equipmentService.getAll().spliterator(),
                false).toList();
        model.addAttribute("otherEquipments", otherEquipments);
    }

    @PostMapping("/location/more/{id}/list")
    public String update(@PathVariable("id") String id,
                         @RequestParam(value = "equipment") String equipmentAsString, Model model){
        Equipment equipment = equipmentService.getById(Long.parseLong(equipmentAsString));
        equipment.setLocation(locationService.getById(Long.parseLong(id)));
        equipmentService.save(equipment);

        getAndLoad(model, id);
        return "location_equipment_list";
    }
}

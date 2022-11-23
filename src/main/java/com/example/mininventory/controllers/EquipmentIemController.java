package com.example.mininventory.controllers;

import com.example.mininventory.models.Equipment;
import com.example.mininventory.models.Location;
import com.example.mininventory.services.EquipmentService;
import com.example.mininventory.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.StreamSupport;

@Controller
public class EquipmentIemController {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private LocationService locationService;

    @GetMapping("/equipment/more/{id}")
    public String more(@PathVariable("id") String id, Model model){

        getAndLoadLocation(model, id);

        return "equipment_item_control";
    }

    @PostMapping("/equipment/more/{id}")
    public String update(@PathVariable("id") String id,
                         @RequestParam(value = "inventoryNumber") String inventoryNumberAsString,
                         @RequestParam(value = "weight") String weightAsString,
                         @RequestParam(value = "yearOfEntry") String yearOfEntryAsString,
                         @RequestParam(value = "count") String countAsString,
                         @RequestParam(value = "location") String locationAsString, Model model){
        equipmentService.updateFromView(id, inventoryNumberAsString, weightAsString, yearOfEntryAsString, countAsString,
                locationAsString);

        getAndLoadLocation(model, id);

        return "equipment_item_control";
    }

    @PostMapping("/equipment/more/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model){
        try{
            equipmentService.deleteById(Long.parseLong(id));
            return "redirect:/equipment";
        } catch (Exception exception) {
            getAndLoadLocation(model, id);
            return "redirect:/equipment/more/{id}";
        }
    }

    private void getAndLoadLocation(Model model, String id){
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        model.addAttribute("locations", locations);

        Equipment equipment = equipmentService.getById(Long.parseLong(id));
        model.addAttribute("selectedEquipment", equipment);
        model.addAttribute("weight",
                String.valueOf(equipment.getWeight()).replace(',', '.'));
    }
}

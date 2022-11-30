package com.example.mininventory.controllers;

import com.example.mininventory.models.Equipment;
import com.example.mininventory.models.Location;
import com.example.mininventory.services.EquipmentService;
import com.example.mininventory.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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
        loadListWithSubList(model);
        return "equipment_control";
    }

    private void loadSubList(Model model){
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        model.addAttribute("locations", locations);
    }

    private void loadListWithSubList(Model model){
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        model.addAttribute("locations", locations);

        loadSubList(model);
    }

    @GetMapping("/equipment/exact")
    public String exactSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Equipment> equipments = equipmentService.getResultExactSearch(query);

            loadSubList(model);

            model.addAttribute("equipments", equipments);

            return "equipment_control";
        } else return "redirect:/equipment";
    }

    @GetMapping("/equipment/imprecise")
    public String impreciseSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Equipment> equipments = equipmentService.getResultImpreciseSearch(query);

            loadSubList(model);

            model.addAttribute("equipments", equipments);

            return "equipment_control";
        } else return "redirect:/equipment";
    }

    @PostMapping("/equipment")
    public String add(@Valid @ModelAttribute(value = "selectedEquipment") Equipment equipment,
                      BindingResult bindingResult, Model model, // specific is down
                      @RequestParam(value = "location") String locationAsString){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            try {
                equipment.setLocation(locationService.getById(Long.parseLong(locationAsString)));
            } catch (Exception ignored) {
                equipment.setLocation(equipmentService.getById(equipment.getId()).getLocation());
            }
            equipmentService.save(equipment);
        }

        loadListWithSubList(model);
        return "equipment_control";
    }
}

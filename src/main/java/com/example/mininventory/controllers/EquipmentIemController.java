package com.example.mininventory.controllers;

import com.example.mininventory.models.Equipment;
import com.example.mininventory.models.Location;
import com.example.mininventory.services.EquipmentService;
import com.example.mininventory.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Controller
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
public class EquipmentIemController {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private LocationService locationService;

    @GetMapping("/equipment/more/{id}")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "equipment_item_control";
    }

    private void getAndLoad(Model model, String id){
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        model.addAttribute("locations", locations);

        Equipment equipment = equipmentService.getById(Long.parseLong(id));
        model.addAttribute("selectedEquipment", equipment);
        model.addAttribute("weight",
                String.valueOf(equipment.getWeight()).replace(',', '.'));
    }

    @PostMapping("/equipment/more/{id}")
    public String update(@Valid @ModelAttribute(value = "selectedEquipment") Equipment equipment,
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

        getAndLoad(model, equipment.getId().toString());
        return "equipment_item_control";
    }

    @PostMapping("/equipment/more/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            equipmentService.deleteById(Long.parseLong(id));
            return "redirect:/equipment";
        } catch (Exception exception) {
            getAndLoad(model, id);
            return "redirect:/equipment/more/{id}";
        }
    }
}

package com.example.mininventory.services;

import com.example.mininventory.models.Equipment;
import com.example.mininventory.models.Location;
import com.example.mininventory.repos.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private LocationService locationService;

    public Iterable<Equipment> getAll() { return equipmentRepository.findAll(); }

    public List<Equipment> getResultExactSearch(String query) {
        return equipmentRepository.findByInventoryNumber(query);
    }

    public List<Equipment> getResultImpreciseSearch(String query) {
        List<Equipment> desired = new ArrayList<>();

        for (Equipment equipment : equipmentRepository.findAll())
            if (equipment.getInventoryNumber().contains(query)) desired.add(equipment);

        return desired;
    }

    public void updateFromView(String id, String inventoryNumber, String weightAsString, String yearOfEntryAsString,
                               String countAsString, String locationAsString){
        Equipment equipment = convertDataFromForm(inventoryNumber, weightAsString, yearOfEntryAsString,
                countAsString, locationAsString);
        equipment.setId(Long.parseLong(id));

        equipmentRepository.save(equipment);
    }

    public void addFromView(String inventoryNumber, String weightAsString, String yearOfEntryAsString,
                            String countAsString, String locationAsString){
        equipmentRepository.save(convertDataFromForm(inventoryNumber, weightAsString, yearOfEntryAsString,
                countAsString, locationAsString));
    }

    private Equipment convertDataFromForm(String inventoryNumber, String weightAsString, String yearOfEntryAsString,
                                         String countAsString, String locationAsString){
        double weight = Double.parseDouble(weightAsString);
        Year yearOfEntry = Year.parse(yearOfEntryAsString);
        int count = Integer.parseInt(countAsString);
        Location location = locationService.getById(Long.parseLong(locationAsString));

        return new Equipment(inventoryNumber, weight, yearOfEntry, count, location);
    }

    public Equipment getById(Long id){
        return equipmentRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id) { equipmentRepository.deleteById(id); }
}

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

    public void add(Equipment equipment){
        equipmentRepository.save(equipment);
    }

    public void addFromView(String inventoryNumber, String weightAsString, String yearOfEntryAsString,
                            String countAsString, String locationAsString){
        double weight = Double.parseDouble(weightAsString);
        Year yearOfEntry = Year.parse(yearOfEntryAsString);
        int count = Integer.parseInt(countAsString);
        Location location = locationService.getById(Long.parseLong(locationAsString));

        Equipment equipment = new Equipment(inventoryNumber, weight, yearOfEntry, count, location);

        equipmentRepository.save(equipment);
    }
}

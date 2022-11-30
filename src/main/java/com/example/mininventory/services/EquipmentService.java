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

    public void save(Equipment equipment){
        equipmentRepository.save(equipment);
    }

    public Equipment getById(Long id){
        return equipmentRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id) { equipmentRepository.deleteById(id); }
}

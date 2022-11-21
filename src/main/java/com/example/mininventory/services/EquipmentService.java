package com.example.mininventory.services;

import com.example.mininventory.models.Equipment;
import com.example.mininventory.repos.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

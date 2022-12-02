package com.example.mininventory.repos;

import com.example.mininventory.models.Equipment;
import com.example.mininventory.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long> {
    List<Equipment> findByInventoryNumber(String number);
    List<Equipment> findByLocation(Location location);
}

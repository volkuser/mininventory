package com.example.mininventory.repos;

import com.example.mininventory.models.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findAll();
    //List<Location> findAllByNumberOrLetter(String NumberOrLetter);
}

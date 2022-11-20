package com.example.mininventory.repos;

import com.example.mininventory.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    //List<Location> getAll();
    //List<Location> findAllByNumberOrLetter(String NumberOrLetter);
}

package com.example.mininventory.repos;

import com.example.mininventory.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findByNumber(byte number);
    List<Location> findByAdditionLetter(char letter);
    Optional<Location> findById(Long id);
}

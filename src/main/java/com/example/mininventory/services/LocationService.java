package com.example.mininventory.services;

import com.example.mininventory.models.Location;
import com.example.mininventory.repos.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public Iterable<Location> getAll(){
        return locationRepository.findAll();
    }

    public void add(Location location){
        locationRepository.save(location);
    }
}

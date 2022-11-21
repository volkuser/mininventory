package com.example.mininventory.services;

import com.example.mininventory.models.Location;
import com.example.mininventory.repos.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public Iterable<Location> getAll(){
        return locationRepository.findAll();
    }

    public List<Location> getResultExactSearch(String query) {
        List<Location> desiredByNumber = new ArrayList<>(), desiredByLetter = new ArrayList<>();
        try {
            desiredByNumber = locationRepository.findByNumber(Byte.parseByte(query));
        } catch (Exception ignored) { }
        try {
            desiredByLetter = locationRepository.findByAdditionLetter(query.charAt(0));
        } catch (Exception ignored) { }

        List<Location> desired = desiredByNumber;

        boolean match = false;
        for (Location locationWithLetter : desiredByLetter){
            for (Location locationWithNumber : desiredByNumber) {
                if (locationWithNumber.equals(locationWithLetter)) {
                    match = true;
                    break;
                }
            }
            if (!match) {
                desired.add(locationWithLetter);
                match = false;
            }
        }

        return desired;
    }

    public void add(Location location){
        locationRepository.save(location);
    }

    public void addFromView(String numberAsString, String additionLetterAsString, String isAuditoryAsString,
                            String operatingHoursAsString, String openFromAsString){
        byte number = Byte.parseByte(numberAsString);
        char additionLetter = (additionLetterAsString.isEmpty()) ? 'x' : additionLetterAsString.charAt(0);
        boolean isAuditory = isAuditoryAsString != null;
        Time operatingHours = Time.valueOf(operatingHoursAsString + ":00");
        Date openFrom = Date.valueOf(openFromAsString);

        Location location = new Location(number, additionLetter, isAuditory, operatingHours, openFrom);
        locationRepository.save(location);
    }
}

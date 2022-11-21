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

    private List<Location> fusion(List<Location> first, List<Location> second){
        List<Location> fusion = first;

        boolean match = false;
        for (Location locationInSecond : second){
            for (Location locationInFirst : first) {
                if (locationInFirst.equals(locationInSecond)) {
                    match = true;
                    break;
                }
            }
            if (!match) {
                fusion.add(locationInSecond);
                match = false;
            }
        }

        return fusion;
    }

    public List<Location> getResultExactSearch(String query) {
        List<Location> desiredByNumber = new ArrayList<>(), desiredByLetter = new ArrayList<>();
        try {
            desiredByNumber = locationRepository.findByNumber(Byte.parseByte(query));
        } catch (Exception ignored) { }
        try {
            desiredByLetter = locationRepository.findByAdditionLetter(query.charAt(0));
        } catch (Exception ignored) { }

        return fusion(desiredByNumber, desiredByLetter);
    }

    public List<Location> getResultImpreciseSearch(String query) {
        List<Location> desiredByNumber = new ArrayList<>(), desiredByLetter = new ArrayList<>();
        for (Location location : locationRepository.findAll())
            if (Byte.toString(location.getNumber()).contains(query)) desiredByNumber.add(location);
        try {
            // imprecise search for char is identical to exact search for char
            desiredByLetter = locationRepository.findByAdditionLetter(query.charAt(0));
        } catch (Exception ignored) { }

        return fusion(desiredByNumber, desiredByLetter);
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

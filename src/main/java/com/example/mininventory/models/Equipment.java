package com.example.mininventory.models;

import javax.persistence.*;
import java.time.Year;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String inventoryNumber;
    private double weight;
    private Year yearOfEntry;
    private int count;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Equipment() { }

    public Equipment(String inventoryNumber, double weight, Year yearOfEntry, int count, Location location) {
        this.inventoryNumber = inventoryNumber;
        this.weight = weight;
        this.yearOfEntry = yearOfEntry;
        this.count = count;
        this.location = location;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Year getYearOfEntry() {
        return yearOfEntry;
    }

    public void setYearOfEntry(Year yearOfEntry) {
        this.yearOfEntry = yearOfEntry;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
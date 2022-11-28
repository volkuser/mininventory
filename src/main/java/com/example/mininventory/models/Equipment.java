package com.example.mininventory.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Year;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank(message = "value cannot be empty")
    @Size(min = 4, max = 11, message = "value must be in range of 4 to 11")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9-]+$", message = "pattern for value - \"a-zA-Zа-яА-Я0-9-\"")
    private String inventoryNumber;
    private double weight;
    private Year yearOfEntry;
    @Max(value = 50, message = "max value is 50")
    @Positive(message = "value cannot be negative or null")
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
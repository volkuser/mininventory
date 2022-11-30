package com.example.mininventory.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Positive(message = "value cannot be negative or null")
    private byte number;
    private char additionLetter;
    private boolean isAuditory;
    private Time operatingHours;
    @NotNull(message = "value cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent(message = "date must be in past or present")
    private Date openFrom;

    @OneToMany
    @JoinColumn(name = "equipment_id")
    private Set<Equipment> equipments = new HashSet<>();

    public Location() { }

    public Location(byte number, char additionLetter, boolean isAuditory, Time operatingHours, Date openFrom) {
        this.number = number;
        this.additionLetter = additionLetter;
        this.isAuditory = isAuditory;
        this.operatingHours = operatingHours;
        this.openFrom = openFrom;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public byte getNumber() { return number; }

    public void setNumber(byte number) {
        this.number = number;
    }

    public char getAdditionLetter() {
        return additionLetter;
    }

    public void setAdditionLetter(char additionLetter) {
        this.additionLetter = additionLetter;
    }

    public boolean isAuditory() {
        return isAuditory;
    }

    public void setAuditory(boolean auditory) {
        isAuditory = auditory;
    }

    public Time getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(Time operatingHours) {
        this.operatingHours = operatingHours;
    }

    public Date getOpenFrom() {
        return openFrom;
    }

    public void setOpenFrom(Date openFrom) {
        this.openFrom = openFrom;
    }
}
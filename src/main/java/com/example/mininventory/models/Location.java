package com.example.mininventory.models;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Positive(message = "value cannot be negative")
    private byte number;
    private char additionLetter;
    private boolean isAuditory;
    private Time operatingHours;
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    @PastOrPresent(message = "date must be in past or present")
    private Date openFrom;

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
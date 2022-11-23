package com.example.mininventory.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private byte number;
    private char additionLetter;
    private boolean isAuditory;
    private Time OperatingHours;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date OpenFrom;

    public Location() { }

    public Location(byte number, char additionLetter, boolean isAuditory, Time operatingHours, Date openFrom) {
        this.number = number;
        this.additionLetter = additionLetter;
        this.isAuditory = isAuditory;
        OperatingHours = operatingHours;
        OpenFrom = openFrom;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public byte getNumber() {
        return number;
    }

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
        return OperatingHours;
    }

    public void setOperatingHours(Time operatingHours) {
        OperatingHours = operatingHours;
    }

    public Date getOpenFrom() {
        return OpenFrom;
    }

    public void setOpenFrom(Date openFrom) {
        OpenFrom = openFrom;
    }
}
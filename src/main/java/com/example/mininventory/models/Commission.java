package com.example.mininventory.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private int number;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date eventDate;

    @ManyToMany
    @JoinTable(name = "employee_in_commission",
            joinColumns = {
                    @JoinColumn(name = "employee_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "commission_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Commission> commissions = new HashSet<>();

    public Commission(int number, Date eventDate) {
        this.number = number;
        this.eventDate = eventDate;
    }

    public Commission() { }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
}

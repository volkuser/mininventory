package com.example.mininventory.models;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank(message = "value cannot be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9]+$", message = "pattern for value - \"a-zA-Zа-яА-Я0-9\"")
    private String number;
    @NotNull(message = "value cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "date must be in future")
    private Date eventDate;
    @Nullable
    @Transient
    private String eventDateAsString;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_in_commission",
            joinColumns = {
                    @JoinColumn(name = "employee_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "commission_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Commission> commissions = new HashSet<>();

    public Commission(String number, Date eventDate) {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Nullable
    public String getEventDateAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(eventDate);
    }
}

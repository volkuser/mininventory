package com.example.mininventory.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank(message = "value cannot be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "only letters should be used in the value")
    private String surname;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_in_commission",
            joinColumns = {
                    @JoinColumn(name = "employee_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "commission_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Commission> commissions = new HashSet<>();

    public Employee() { }

    public Employee(String surname, User user) {
        this.surname = surname;
        this.user = user;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Commission> getCommissions() {
        return commissions;
    }

    public void setCommissions(Set<Commission> commissions) {
        this.commissions = commissions;
    }

    public void addInCommissions(Commission commission) {
        this.commissions.add(commission);
    }

    public void deleteFromCommissions(Commission commission) {
        this.commissions.remove(commission);
    }
}
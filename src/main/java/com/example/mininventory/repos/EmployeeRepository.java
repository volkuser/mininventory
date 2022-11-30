package com.example.mininventory.repos;

import com.example.mininventory.models.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository {
    Iterable<Employee> findAll();
    List<Employee> findBySurname(String surname);

    void save(Employee user);

    Optional<Employee> findById(Long id);
    void deleteById(Long id);
}

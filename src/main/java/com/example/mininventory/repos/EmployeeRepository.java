package com.example.mininventory.repos;

import com.example.mininventory.models.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository {
    List<Employee> findBySurname(String Email);
}

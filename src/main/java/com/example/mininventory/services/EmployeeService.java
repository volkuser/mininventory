package com.example.mininventory.services;

import com.example.mininventory.models.Employee;
import com.example.mininventory.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Iterable<Employee> getAll() { return employeeRepository.findAll(); }

    public List<Employee> getResultExactSearch(String query) {
        return employeeRepository.findBySurname(query);
    }

    public List<Employee> getResultImpreciseSearch(String query) {
        List<Employee> desired = new ArrayList<>();

        for (Employee user : employeeRepository.findAll())
            if (user.getSurname().contains(query)) desired.add(user);

        return desired;
    }

    public void save(Employee user){
        employeeRepository.save(user);
    }

    public Employee getById(Long id){
        return employeeRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id) { employeeRepository.deleteById(id); }
}

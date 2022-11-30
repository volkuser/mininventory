package com.example.mininventory.services;

import com.example.mininventory.models.Commission;
import com.example.mininventory.repos.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommissionService {
    @Autowired
    private CommissionRepository commissionRepository;

    public Iterable<Commission> getAll() { return commissionRepository.findAll(); }

    public List<Commission> getResultExactSearch(String query) {
        return commissionRepository.findByNumber(query);
    }

    public List<Commission> getResultImpreciseSearch(String query) {
        List<Commission> desired = new ArrayList<>();

        for (Commission commission : commissionRepository.findAll())
            if (commission.getNumber().contains(query)) desired.add(commission);

        return desired;
    }

    public void save(Commission commission){
        commissionRepository.save(commission);
    }

    public Commission getById(Long id){
        return commissionRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id) { commissionRepository.deleteById(id); }
}

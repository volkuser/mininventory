package com.example.mininventory.repos;

import com.example.mininventory.models.Commission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommissionRepository {
    Iterable<Commission> findAll();
    List<Commission> findByNumber(String number);

    void save(Commission commission);

    Optional<Commission> findById(Long id);
    void deleteById(Long id);
}

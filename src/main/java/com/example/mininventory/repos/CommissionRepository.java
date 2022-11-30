package com.example.mininventory.repos;

import com.example.mininventory.models.Commission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommissionRepository {
    List<Commission> findByNumber(String number);
}

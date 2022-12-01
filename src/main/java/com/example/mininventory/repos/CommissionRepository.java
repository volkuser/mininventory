package com.example.mininventory.repos;

import com.example.mininventory.models.Commission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommissionRepository extends CrudRepository<Commission, Long> {
    List<Commission> findByNumber(String number);
}

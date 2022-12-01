package com.example.mininventory.repos;

import com.example.mininventory.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    List<User> findByEmail(String Email);
}

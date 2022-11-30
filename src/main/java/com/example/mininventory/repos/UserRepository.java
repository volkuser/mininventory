package com.example.mininventory.repos;

import com.example.mininventory.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    List<User> findByEmail(String Email);
}

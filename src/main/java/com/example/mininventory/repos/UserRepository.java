package com.example.mininventory.repos;

import com.example.mininventory.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    Iterable<User> findAll();
    List<User> findByEmail(String Email);

    void save(User user);

    Optional<User> findById(Long id);
    void deleteById(Long id);
}

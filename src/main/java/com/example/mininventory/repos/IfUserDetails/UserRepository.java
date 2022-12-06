package com.example.mininventory.repos.IfUserDetails;

import com.example.mininventory.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

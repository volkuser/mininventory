/*
package com.example.mininventory.services;

import com.example.mininventory.models.User;
import com.example.mininventory.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAll() { return userRepository.findAll(); }

    public List<User> getResultExactSearch(String query) {
        return userRepository.findByEmail(query);
    }

    public List<User> getResultImpreciseSearch(String query) {
        List<User> desired = new ArrayList<>();

        for (User user : userRepository.findAll())
            if (user.getEmail().contains(query)) desired.add(user);

        return desired;
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User getById(Long id){
        return userRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id) { userRepository.deleteById(id); }
}
*/

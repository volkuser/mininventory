package com.example.mininventory.services.IfUserDetails;

import com.example.mininventory.models.Employee;
import com.example.mininventory.models.Role;
import com.example.mininventory.models.User;
import com.example.mininventory.repos.IfUserDetails.UserRepository;
import com.example.mininventory.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(User user, Employee employee){
        if (userRepository.findByEmail(user.getEmail()) != null) return false;

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addInRoles(Role.ROLE_USER);

        employeeService.save(employee);
        user.setEmployee(StreamSupport.stream(employeeService.getAll().spliterator(),
                false).toList().get(StreamSupport.stream(employeeService.getAll().spliterator(),
                false).toList().size() - 1));

        userRepository.save(user);

        return true;
    }
}

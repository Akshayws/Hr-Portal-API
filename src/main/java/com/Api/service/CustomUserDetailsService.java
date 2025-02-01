package com.Api.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Api.model.Employee;
import com.Api.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

//to find user password and Email from database
//This service is specifically used for Spring Security authentication.
//It implements UserDetailsService, which Spring Security requires
//to fetch user details from the database during login.
//Fetching user details for Spring Security authentication
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);

        if (employee == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Directly access the employee's role since only one role exists
        String roleName = employee.getRole().getRoleName();  // Assuming you have a Role object with getRoleName()

        // Convert role to GrantedAuthority format for Spring Security
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleName);


        return new User(
                employee.getEmail(), 
                employee.getPassword(), 
                Collections.singletonList(authority)  // Convert role to authorities
        );
    }
}

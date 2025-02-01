package com.Api.service;

// when we want new Employee to add by HR and can add password and Email for new user
//Creating, retrieving users, and hashing passwords
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Api.model.User;
import com.Api.repository.UserRepository;



@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection for UserRepository and PasswordEncoder
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new user (employee login credentials).
     * @param email The email of the new user.
     * @param password The plain password for the new user.
     * @param role The role of the user (e.g., ROLE_EMPLOYEE, ROLE_HR).
     * @return The saved user entity.
     */
    public User createUser(String email, String password, String role) {
        // Check if the user already exists based on the email
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User with email already exists");
        }

        // Create a new user entity
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));  // Encode the password
        user.setRole(role);

        // Save the new user to the database
        return userRepository.save(user);
    }

    /**
     * Fetches a user by email.
     * @param email The email of the user.
     * @return The user entity if found, or an empty Optional.
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

package com.Api.service;


import java.util.Optional;

//Validating credentials during manual login
//✅ Matches hashed password with the stored password
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Api.dto.LoginRequest;
import com.Api.dto.LoginResponse;
import com.Api.model.User;
import com.Api.repository.UserRepository;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to handle login logic
    public LoginResponse login(LoginRequest request) {
        // Check if the user exists by email
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Verify the password matches the stored (encoded) password
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                // If password is correct, return a successful login response
                return new LoginResponse("Login successful", user.getRole());
            }
        }
        
        // If user not found or password doesn't match
        throw new RuntimeException("Invalids credentials");
    }
}


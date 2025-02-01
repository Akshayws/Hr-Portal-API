package com.Api.controller;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Api.dto.LoginRequest;
import com.Api.dto.LoginResponse;
import com.Api.model.User;
import com.Api.repository.UserRepository;
import com.Api.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	 private final AuthService authService;
	 private final UserRepository userRepository;

	    public AuthController(AuthService authService, UserRepository userRepository) {
	        this.authService = authService;
	        this.userRepository = userRepository;
	    }

	    @PostMapping("/login")
	    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
	        return ResponseEntity.ok(authService.login(request));
	    }

	    @GetMapping("/checkUser/{email}")
	    public ResponseEntity<String> checkUserExists(@PathVariable String email) {
	        Optional<User> userOptional = userRepository.findByEmail(email);
	        if (userOptional.isPresent()) {
	            return ResponseEntity.ok("User exists");
	        } else {
	            return ResponseEntity.ok("User does not exist");
	        }
	    }
	}

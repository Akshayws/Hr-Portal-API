package com.Api.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.Api.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query to find a user by email
    Optional<User> findByEmail(String email);
}

package com.hr.server.repository;

import com.hr.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find a user by email (used for authentication)
    Optional<User> findByEmail(String email);
}
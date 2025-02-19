package com.hr.server.repository;

import com.hr.server.model.Employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // You can define custom queries if needed. Example:
    
	 Optional<Employee> findByEmail(String email);
	Optional<Employee> findById(Long employeeId);
	@Query("SELECT e FROM Employee e JOIN FETCH e.department JOIN FETCH e.role WHERE e.email = :email")
	Employee findByEmailWithDetails(@Param("email") String email);

     
     
}

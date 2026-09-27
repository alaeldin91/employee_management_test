package com.alaeldin.employee_anagement_service.repository;

import com.alaeldin.employee_anagement_service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository  extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByEmail(String email);
}

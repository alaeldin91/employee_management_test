package com.alaeldin.employee_anagement_service.repository;

import com.alaeldin.employee_anagement_service.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DepartmentRepository  extends JpaRepository<Department, UUID> {

    Optional<Department> findByDepartmentName(String departmentName);
    Optional<Department> findByDepartmentCode(String departmentCode);
    boolean existsByDepartmentCode(String departmentCode);
}

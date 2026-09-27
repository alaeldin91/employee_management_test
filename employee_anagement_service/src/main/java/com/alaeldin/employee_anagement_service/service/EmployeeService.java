package com.alaeldin.employee_anagement_service.service;

import com.alaeldin.employee_anagement_service.Util;
import com.alaeldin.employee_anagement_service.dto.EmployeeRequest;
import com.alaeldin.employee_anagement_service.dto.EmployeeResponse;
import com.alaeldin.employee_anagement_service.dto.PageResponse;
import com.alaeldin.employee_anagement_service.exception.ResourceNotFound;
import com.alaeldin.employee_anagement_service.mapper.EmployeeMapper;
import com.alaeldin.employee_anagement_service.mapper.PageResponseMapper;
import com.alaeldin.employee_anagement_service.model.Department;
import com.alaeldin.employee_anagement_service.model.Employee;
import com.alaeldin.employee_anagement_service.repository.DepartmentRepository;
import com.alaeldin.employee_anagement_service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentRepository departmentRepository;
    private final PageResponseMapper employeePageResponseMapper;
    private final Util util;

    @Transactional
    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {

        Department department = departmentRepository.findById(employeeRequest.getDepartment())
                .orElseThrow(() -> new ResourceNotFound("department", "id", employeeRequest.getDepartment()));

        Employee employee = employeeMapper.toMapEmployeeEntity(employeeRequest);

        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toMapEmployeeResponse(savedEmployee);
    }


    @Transactional
    public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest, UUID id) {

        Employee existEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Employee", "id", id.toString()));

        Department department = departmentRepository.findById(employeeRequest.getDepartment())
                .orElseThrow(() -> new ResourceNotFound("Department", "id", employeeRequest.getDepartment()));
        employeeMapper.updateEmployeeFromRequest(employeeRequest,existEmployee);

        existEmployee.setDepartment(department);
        existEmployee.setUpdatedAt(LocalDateTime.now());

        return employeeMapper.toMapEmployeeResponse(existEmployee);
    }

    @Transactional
    public void deleteEmployee(UUID id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Employee", "id", id.toString()));

        employeeRepository.delete(employee);
    }

    @Transactional(readOnly = true)
    public PageResponse<EmployeeResponse> getEmployees(Pageable pageable) {

        util.validatePageable(pageable);

        log.debug("Fetching employees: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());

        Page<Employee> employees = employeeRepository.findAll(pageable);
        Page<EmployeeResponse> employeeResponses = employees.map(employeeMapper::toMapEmployeeResponse);

        return employeePageResponseMapper.from(employeeResponses);
    }

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployee(UUID id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Employee", "id", id.toString()));

        return employeeMapper.toMapEmployeeResponse(employee);
    }
}

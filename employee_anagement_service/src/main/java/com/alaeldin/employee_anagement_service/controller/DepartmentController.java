package com.alaeldin.employee_anagement_service.controller;

import com.alaeldin.employee_anagement_service.dto.DepartmentRequest;
import com.alaeldin.employee_anagement_service.dto.DepartmentResponse;
import com.alaeldin.employee_anagement_service.dto.PageResponse;
import com.alaeldin.employee_anagement_service.repository.DepartmentRepository;
import com.alaeldin.employee_anagement_service.service.DepartmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController
{
    private final DepartmentService departmentService;


    @PostMapping()
    public ResponseEntity<DepartmentResponse> saveDepartment(@Valid @RequestBody DepartmentRequest departmentRequest)
    {
        DepartmentResponse department = departmentService.saveDepartment(departmentRequest);
        return   new  ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@Valid @PathVariable UUID id)
    {
        departmentService.deleteDepartment(id);

        return   new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse>getDepartment(@PathVariable UUID id)
    {
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<PageResponse<DepartmentResponse>> getAllDepartments(
            @RequestParam(defaultValue = "0")
            @Min(value = 0 , message = "Page number must be Zero Or a positive integer")
            Integer pageNumber,
            @RequestParam(defaultValue = "10")
            @Min(value = 1 , message = "Page size must be a positive integer")
            @Max(value = 100 , message = "Page size must be less than or equal to 100")
            Integer pageSize
    )
    {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return new ResponseEntity<>(departmentService.getAllDepartments(pageRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@Valid @PathVariable UUID id, @Valid @RequestBody DepartmentRequest departmentRequest )
    {
        DepartmentResponse department = departmentService.updateDepartment(departmentRequest ,id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}

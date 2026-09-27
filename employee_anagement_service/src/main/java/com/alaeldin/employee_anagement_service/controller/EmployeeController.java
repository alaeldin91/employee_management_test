package com.alaeldin.employee_anagement_service.controller;

import com.alaeldin.employee_anagement_service.dto.EmployeeRequest;
import com.alaeldin.employee_anagement_service.dto.EmployeeResponse;
import com.alaeldin.employee_anagement_service.dto.PageResponse;
import com.alaeldin.employee_anagement_service.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController
{

      private final EmployeeService employeeService;

      @PostMapping()
      public ResponseEntity<EmployeeResponse> saveEmployee(@Valid @RequestBody EmployeeRequest employeeRequest)
      {
          return new  ResponseEntity<>(employeeService.saveEmployee(employeeRequest)
                  , HttpStatus.CREATED);
      }

      @GetMapping("/{id}")
      public ResponseEntity<EmployeeResponse> getEmployee(@Valid @PathVariable UUID id)
      {
          return new ResponseEntity<>(employeeService.getEmployee(id)
                  , HttpStatus.OK);
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<Void> deleteEmployee(@Valid @PathVariable UUID id)
      {
          employeeService.deleteEmployee(id);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      @GetMapping()
      public ResponseEntity<PageResponse<EmployeeResponse>> getAllEmployees(
              @RequestParam(defaultValue = "0")
              @Min(value = 0 , message = " Page Number Must  0 Or Positive Number")
              Integer pageNumber,
              @RequestParam( defaultValue = "10")
              @Min(value = 1 , message = "Page Size must be a Positive ")
              @Max(value = 100
                      , message = "Page Size must be less than or equal to 100")
              Integer pageSize

      )
      {
          Pageable pageable = PageRequest.of(pageNumber, pageSize);

          return new ResponseEntity<>(employeeService.getEmployees(pageable)
                  , HttpStatus.OK);
      }

      @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @Valid @PathVariable UUID id,
            @Valid @RequestBody EmployeeRequest employeeRequest
      ){
          return new ResponseEntity<>(employeeService.updateEmployee(employeeRequest,id )
                  , HttpStatus.OK);
      }
}

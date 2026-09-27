package com.alaeldin.employee_anagement_service.dto;

import com.alaeldin.employee_anagement_service.enums.GenderType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeResponse {

    private UUID employeeId; //this Employee Id Is Generate Automatic UUID
    private String name; // this Employee Name Is Not Null
    private String email; //this Employee Email Is Not Null And Type Is Email Only
    private GenderType gender;  // This Employee Gender Type is (Male Or Female)
    private BigDecimal salary; //This Employee Salary is Positive or Zero Not Null
    private DepartmentResponse department; //This employee Relation with Department
    private LocalDate hireDate; //This employee Hire Date
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

package com.alaeldin.employee_anagement_service.dto;

import com.alaeldin.employee_anagement_service.enums.GenderType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeRequest
{

    @NotBlank
    @Size(min = 3, max = 40)
    private String name; // this Employee Name Is Not Null
    @NotBlank
    @Size(min = 10, max = 40)
    @Email
    private String email; //this Employee Email Is Not Null And Type Is Email Only
    @NotNull
    private GenderType gender;  // This Employee Gender Type is (Male Or Female)
    @PositiveOrZero
    @NotNull
    private BigDecimal salary; //This Employee Salary is Positive or Zero Not Null
    @NotNull
    private UUID department; //This employee Relation with Department
    @NotNull
    private LocalDate hireDate; //This employee Hire Date

}

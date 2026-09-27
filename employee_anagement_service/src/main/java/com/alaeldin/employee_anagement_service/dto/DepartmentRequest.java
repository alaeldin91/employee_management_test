package com.alaeldin.employee_anagement_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String departmentName;
    @NotBlank
    @Size(min = 3, max = 10)
    private String departmentCode;
}

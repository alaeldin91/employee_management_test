package com.alaeldin.employee_anagement_service.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentResponse {

    private UUID id;
    private String departmentName;
    private String departmentCode;

}

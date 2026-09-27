package com.alaeldin.employee_anagement_service.dto;

import com.alaeldin.employee_anagement_service.enums.Role;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse
{

    private UUID id;
    private String firstname;
    private String lastName;
    private String email;
    private Role role;
    private String token;

}

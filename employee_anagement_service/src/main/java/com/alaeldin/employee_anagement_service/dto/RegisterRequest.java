package com.alaeldin.employee_anagement_service.dto;

import com.alaeldin.employee_anagement_service.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest
{
    @NotBlank
    @Size(min = 3, max = 45)
    private String firstname;
    @NotBlank
    @Size(min = 3, max = 45)
    private String lastName;
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
    @NotBlank
    @Email
    @Size(min = 10, max = 100)
    private String email;
    @NotNull
    private Role role;

}

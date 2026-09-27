package com.alaeldin.employee_anagement_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest
{
    @NotBlank
    @Size(min=10, max=100)
    @Email
    private String email;
    @NotBlank
    @Size(min=6, max=100)
    private String password;

}

package com.alaeldin.employee_anagement_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "department" ,  indexes = {
        @Index(name = "idx_department_name" , columnList = "department_name"),
        @Index(name = "idx_department_code" , columnList = "department_code")
})
public class Department
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "department_name" , nullable = false , unique = true)
    @NotBlank
    @Size(min = 3, max = 100)
    private String departmentName;
    @Column(name = "department_code" , nullable = false , unique = true)
    @NotBlank
    @Size(min = 3, max = 10)
    private String departmentCode;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}

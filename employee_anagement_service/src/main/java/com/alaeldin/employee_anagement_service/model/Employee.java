package com.alaeldin.employee_anagement_service.model;

import com.alaeldin.employee_anagement_service.enums.GenderType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "employees" , indexes = {
        @Index(name = "idx_employee_name" , columnList = "name"),
        @Index(name = "idx_employee_department" , columnList = "department_id"),
        @Index(name = "idx_employee_salary" , columnList = "salary"),
        @Index(name = "idx_employee_hire_date" , columnList = "hire_date")
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID employeeId; //this Employee Id Is Generate Automatic UUID
    @Column(name = "name" , nullable = false , length = 40)
    @NotBlank
    @Size(min = 1, max = 40)
    private String name; // this Employee Name Is Not Null
    @Column(name = "email" , nullable = false , length = 100 , unique = true)
    @Email
    @Size(min = 1, max = 100)
    @NotBlank
    private String email; //this Employee Email Is Not Null And Type Is Email Only
    @Enumerated(EnumType.STRING)
    @NotNull
    private GenderType gender;  // This Employee Gender Type is (Male Or Female)
    @Column(nullable = false , precision =  19, scale = 2)
    @PositiveOrZero
    @NotNull
    private BigDecimal salary; //This Employee Salary is Positive or Zero Not Null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id" , nullable = false)
    @NotNull
    private Department department; //This employee Relation with Department
   @Column(name = "hire_date" ,  nullable = false)
   @NotNull
    private LocalDate hireDate; //This employee Hire Date
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}

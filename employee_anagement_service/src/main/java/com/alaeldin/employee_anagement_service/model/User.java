package com.alaeldin.employee_anagement_service.model;

import com.alaeldin.employee_anagement_service.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users" , indexes = {
        @Index(name = "idx_user_email" , columnList = "email")
})
public class User  implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "first_name"  , nullable = false , length = 45)
    @NotBlank
    @Size(min = 3, max = 45)
    private String firstname;
    @Column(name = "last_name" , nullable = false , length = 45)
    @NotBlank
    @Size(min = 3, max = 45)
    private String lastName;
    @Column(name = "password" , nullable = false , length = 200)
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
    @Column(name = "email" , nullable = false , unique = true)
    @NotBlank
    @Email
    @Size(min = 10, max = 100)
    private String email;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @Column(nullable = false)
    private Boolean isEnabled = true;
    @Column(nullable = false)
    private Boolean isAccountNonExpired = true;
    @Column(nullable = false)
    private Boolean isAccountNonLocked = true;
    @Column(nullable = false)
    private Boolean isCredentialsNonExpired = true;
    @Column(nullable = false)
    private Boolean active = true;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(()-> role.name());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


}

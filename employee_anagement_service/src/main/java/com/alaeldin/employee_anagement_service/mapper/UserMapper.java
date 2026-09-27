package com.alaeldin.employee_anagement_service.mapper;

import com.alaeldin.employee_anagement_service.dto.RegisterRequest;
import com.alaeldin.employee_anagement_service.dto.UserResponse;
import com.alaeldin.employee_anagement_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring" , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper
{

    User toEntity(RegisterRequest user);
    UserResponse toDto(User user);

}

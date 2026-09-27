package com.alaeldin.employee_anagement_service.mapper;

import com.alaeldin.employee_anagement_service.dto.DepartmentRequest;
import com.alaeldin.employee_anagement_service.dto.DepartmentResponse;
import com.alaeldin.employee_anagement_service.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper
{

     DepartmentResponse toResponseDepartment(Department department);
     Department tooDepartment(DepartmentRequest departmentRequest);
     void updateDepartmentFromRequestDepartment(DepartmentRequest departmentRequest, @MappingTarget Department department);
}

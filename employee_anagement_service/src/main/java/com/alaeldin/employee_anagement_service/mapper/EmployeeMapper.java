package com.alaeldin.employee_anagement_service.mapper;

import com.alaeldin.employee_anagement_service.dto.EmployeeRequest;
import com.alaeldin.employee_anagement_service.dto.EmployeeResponse;
import com.alaeldin.employee_anagement_service.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring" , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EmployeeMapper {

   EmployeeResponse toMapEmployeeResponse(Employee employee);

   @Mapping(target = "department" , ignore = true)
   Employee toMapEmployeeEntity(EmployeeRequest employeeRequest);
   @Mapping(target = "department" , ignore = true)
   void updateEmployeeFromRequest(EmployeeRequest employeeRequest, @MappingTarget Employee employee);

}

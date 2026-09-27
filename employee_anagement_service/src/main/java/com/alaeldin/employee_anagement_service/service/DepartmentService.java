package com.alaeldin.employee_anagement_service.service;

import com.alaeldin.employee_anagement_service.Util;
import com.alaeldin.employee_anagement_service.dto.DepartmentRequest;
import com.alaeldin.employee_anagement_service.dto.DepartmentResponse;
import com.alaeldin.employee_anagement_service.dto.PageResponse;
import com.alaeldin.employee_anagement_service.enums.Role;
import com.alaeldin.employee_anagement_service.exception.ResourceNotFound;
import com.alaeldin.employee_anagement_service.mapper.DepartmentMapper;
import com.alaeldin.employee_anagement_service.mapper.PageResponseMapper;
import com.alaeldin.employee_anagement_service.model.Department;
import com.alaeldin.employee_anagement_service.repository.DepartmentRepository;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final PageResponseMapper  pageResponseMapper;
    private final Util util;
    private final JwtService jwtService;

    @Transactional
    public DepartmentResponse saveDepartment(DepartmentRequest departmentRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;

        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if (!"ADMIN".equals(role)) {
            throw new IllegalStateException("Not allowed to create department with this role");
        }

        Department department = departmentMapper.tooDepartment(departmentRequest);
        Department savedDepartment = departmentRepository.save(department);

        log.debug("save department : {}", savedDepartment);

        return departmentMapper.toResponseDepartment(savedDepartment);
    }

    @Transactional
    public DepartmentResponse updateDepartment(DepartmentRequest departmentRequest, UUID id) {

        Department existDepartment = departmentRepository.findById(id).orElseThrow(()
                -> new ResourceNotFound("department" , "id", id));
        existDepartment.setId(id);
        departmentMapper.updateDepartmentFromRequestDepartment(departmentRequest, existDepartment);
        log.debug("update department {} with id {} ", existDepartment, id);
        return departmentMapper.toResponseDepartment(existDepartment);
    }

    @Transactional
    public void deleteDepartment(UUID id) {
        Department department = departmentRepository.findById(id).orElseThrow(()
                -> new ResourceNotFound("department" , "id",id));
        log.debug("Delete department with id:{}", id);
        departmentRepository.delete(department);
    }

    @Transactional(readOnly = true)
    public PageResponse<DepartmentResponse> getAllDepartments(Pageable pageable) {
        util.validatePageable(pageable);
        Page<Department> departmentPage = departmentRepository.findAll(pageable);
        Page<DepartmentResponse> departmentResponses = departmentPage.map(departmentMapper::toResponseDepartment);
        log.debug(" departmentResponses : {}", departmentResponses);
        return pageResponseMapper.from(departmentResponses);
    }

    @Transactional(readOnly = true)
    public DepartmentResponse getDepartmentById(UUID id) {

        Department department = departmentRepository.findById(id).orElseThrow(()
                -> new ResourceNotFound("department" , "id",id));
       log.debug("get department {} with id:{}",department ,id);
        return departmentMapper.toResponseDepartment(department);
    }
}

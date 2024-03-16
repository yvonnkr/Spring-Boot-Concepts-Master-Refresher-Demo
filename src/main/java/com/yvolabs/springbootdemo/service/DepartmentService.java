package com.yvolabs.springbootdemo.service;

import com.yvolabs.springbootdemo.dto.DepartmentDto;
import com.yvolabs.springbootdemo.dto.DepartmentRequestDto;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentRequestDto departmentRequest);

    List<DepartmentDto> getDepartments();

    List<DepartmentDto> getDepartmentByNamePaginated(@Nullable String name, Pageable pageable);

}

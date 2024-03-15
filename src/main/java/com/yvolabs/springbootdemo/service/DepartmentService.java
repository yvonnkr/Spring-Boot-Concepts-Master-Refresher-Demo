package com.yvolabs.springbootdemo.service;

import com.yvolabs.springbootdemo.dto.DepartmentDto;
import com.yvolabs.springbootdemo.dto.DepartmentRequestDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentRequestDto departmentRequest);

    List<DepartmentDto> getDepartments();

}

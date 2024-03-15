package com.yvolabs.springbootdemo.service;

import com.yvolabs.springbootdemo.dto.DepartmentDto;
import com.yvolabs.springbootdemo.dto.DepartmentRequestDto;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentRequestDto departmentRequest);

}

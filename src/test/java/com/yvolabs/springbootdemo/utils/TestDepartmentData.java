package com.yvolabs.springbootdemo.utils;

import com.yvolabs.springbootdemo.dto.DepartmentDto;
import com.yvolabs.springbootdemo.dto.DepartmentRequestDto;

public class TestDepartmentData {

    public static DepartmentRequestDto departmentRequestDto() {
        return DepartmentRequestDto.builder()
                .departmentName("some_dept_name")
                .departmentAddress("some_dept_addr")
                .departmentCode("some_dept_code")
                .build();
    }

    public static DepartmentDto departmentDto() {
        return DepartmentDto.builder()
                .departmentId(1L)
                .departmentName("some_dept_name")
                .departmentAddress("some_dept_addr")
                .departmentCode("some_dept_code")
                .build();
    }
}

package com.yvolabs.springbootdemo.utils;

import com.yvolabs.springbootdemo.dto.DepartmentDto;
import com.yvolabs.springbootdemo.dto.DepartmentRequestDto;

import java.util.List;

public class TestDepartmentData {

    public static DepartmentRequestDto departmentRequestDto() {
        return DepartmentRequestDto.builder()
                .departmentName("some_dept_name")
                .departmentAddress("some_dept_addr")
                .departmentCode("some_dept_code")
                .build();
    }

    public static DepartmentRequestDto invalidDepartmentRequestDto() {
        return DepartmentRequestDto.builder()
                .departmentName("")
                .departmentAddress("some_dept_addr")
                .departmentCode("")
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

    public static List<DepartmentDto> departmentsDtos(){
        return List.of(
                DepartmentDto.builder()
                        .departmentId(1L)
                        .departmentName("some_dept_name_1")
                        .departmentAddress("some_dept_addr_1")
                        .departmentCode("some_dept_code_1")
                        .build(),
                DepartmentDto.builder()
                        .departmentId(2L)
                        .departmentName("some_dept_name_2")
                        .departmentAddress("some_dept_addr_2")
                        .departmentCode("some_dept_code_2")
                        .build(),
                DepartmentDto.builder()
                        .departmentId(3L)
                        .departmentName("some_dept_name_3")
                        .departmentAddress("some_dept_addr_3")
                        .departmentCode("some_dept_code_3")
                        .build(),
                DepartmentDto.builder()
                        .departmentId(4L)
                        .departmentName("dev")
                        .departmentAddress("abc123")
                        .departmentCode("123")
                        .build(),
                DepartmentDto.builder()
                        .departmentId(5L)
                        .departmentName("dev")
                        .departmentAddress("abc123")
                        .departmentCode("123")
                        .build()

        );

    }
}

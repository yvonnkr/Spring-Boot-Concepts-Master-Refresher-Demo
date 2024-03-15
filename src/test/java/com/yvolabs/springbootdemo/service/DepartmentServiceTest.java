package com.yvolabs.springbootdemo.service;

import com.yvolabs.springbootdemo.dto.DepartmentDto;
import com.yvolabs.springbootdemo.dto.DepartmentRequestDto;
import com.yvolabs.springbootdemo.utils.TestDepartmentData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    DepartmentService departmentService;

    @Test
    void createDepartment() {
        DepartmentDto departmentDto = TestDepartmentData.departmentDto();
        DepartmentRequestDto departmentRequest = TestDepartmentData.departmentRequestDto();

        when(departmentService.createDepartment(departmentRequest))
                .thenReturn(departmentDto);

        DepartmentDto department = departmentService.createDepartment(departmentRequest);

        verify(departmentService, times(1)).createDepartment(departmentRequest);
        assertNotNull(department);
        assertEquals(departmentDto.getDepartmentId(), department.getDepartmentId());
        assertEquals("some_dept_name", department.getDepartmentName());
        assertEquals("some_dept_addr", department.getDepartmentAddress());
        assertEquals("some_dept_code", department.getDepartmentCode());
    }

    @Test
    void should_getDepartments() {
        when(departmentService.getDepartments())
                .thenReturn(TestDepartmentData.departmentsDtos());

        List<DepartmentDto> departments = departmentService.getDepartments();

        verify(departmentService, times(1)).getDepartments();
        assertEquals(3, departments.size());
        assertEquals(1, departments.get(0).getDepartmentId());
        assertEquals(2, departments.get(1).getDepartmentId());
        assertEquals(3, departments.get(2).getDepartmentId());

    }
}
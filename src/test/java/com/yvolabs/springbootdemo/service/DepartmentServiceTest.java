package com.yvolabs.springbootdemo.service;

import com.yvolabs.springbootdemo.dto.DepartmentDto;
import com.yvolabs.springbootdemo.dto.DepartmentRequestDto;
import com.yvolabs.springbootdemo.utils.TestDepartmentData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

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
        assertEquals(5, departments.size());
        assertEquals(1, departments.get(0).getDepartmentId());
        assertEquals(2, departments.get(1).getDepartmentId());
        assertEquals(3, departments.get(2).getDepartmentId());

    }

    @Test
    void should_getDepartmentsByNameAndSortByIdDesc() {
        List<DepartmentDto> departmentsDtos = TestDepartmentData.departmentsDtos();
        List<DepartmentDto> response = List.of(departmentsDtos.get(4), departmentsDtos.get(3));

        String name = "dev";
        Pageable pageable = Pageable.ofSize(2);

        when(departmentService.getDepartmentByNamePaginated(name, pageable))
                .thenReturn(response);

        List<DepartmentDto> departments = departmentService.getDepartmentByNamePaginated(name, pageable);

        verify(departmentService, times(1))
                .getDepartmentByNamePaginated(name, pageable);

        assertEquals(2, departments.size());
        assertEquals(5, departments.get(0).getDepartmentId());
        assertEquals(4, departments.get(1).getDepartmentId());

    }

    @Test
    void should_getDepartmentById() {
        when(departmentService.getDepartmentById(1L))
                .thenReturn(TestDepartmentData.departmentDto());

        DepartmentDto department = departmentService.getDepartmentById(1L);

        verify(departmentService, times(1)).getDepartmentById(1L);
        assertEquals(1, department.getDepartmentId());
        assertEquals("some_dept_name", department.getDepartmentName());
        assertEquals("some_dept_code", department.getDepartmentCode());


    }
}
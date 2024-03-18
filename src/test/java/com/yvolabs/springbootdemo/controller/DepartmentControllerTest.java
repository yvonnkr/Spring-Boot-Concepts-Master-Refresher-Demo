package com.yvolabs.springbootdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yvolabs.springbootdemo.dto.DepartmentDto;
import com.yvolabs.springbootdemo.dto.DepartmentRequestDto;
import com.yvolabs.springbootdemo.service.DepartmentService;
import com.yvolabs.springbootdemo.utils.TestDepartmentData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    private static final String PATH = "/departments";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;


    @Test
    void should_createDepartment() throws Exception {
        DepartmentDto departmentDto = TestDepartmentData.departmentDto();
        DepartmentRequestDto departmentRequest = TestDepartmentData.departmentRequestDto();

        given(departmentService.createDepartment(any(DepartmentRequestDto.class)))
                .willReturn(departmentDto);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(departmentRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost" + PATH + "/" + departmentDto.getDepartmentId()));

        verify(departmentService).createDepartment(any(DepartmentRequestDto.class));
    }

    @Test
    void should_notCreateDepartmentWithInvalidData() throws Exception {
        DepartmentRequestDto invalidDepartmentRequestDto = TestDepartmentData.invalidDepartmentRequestDto();

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(invalidDepartmentRequestDto)))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(departmentService);
    }

    @Test
    void should_getDepartments() throws Exception {
        List<DepartmentDto> departments = TestDepartmentData.departmentsDtos();

        given(departmentService.getDepartments())
                .willReturn(departments);

        mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departmentId").value(1))
                .andExpect(jsonPath("$[1].departmentId").value(2))
                .andExpect(jsonPath("$[2].departmentId").value(3));

        verify(departmentService).getDepartments();
    }

    @Test
    void should_getDepartmentsByNameAndSortByIdDesc() throws Exception {
        List<DepartmentDto> departments = TestDepartmentData.departmentsDtos();
        List<DepartmentDto> response = List.of(departments.get(4), departments.get(3));

        given(departmentService.getDepartmentByNamePaginated(any(), any()))
                .willReturn(response);

        mockMvc.perform(get(PATH + "/search?departmentName=dev&page=0&size=3&sort=departmentId,desc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departmentId").value(5))
                .andExpect(jsonPath("$[1].departmentId").value(4));

        verify(departmentService).getDepartmentByNamePaginated(any(), any());
    }

    @Test
    void should_getDepartmentById() throws Exception {
        DepartmentDto department = TestDepartmentData.departmentDto();

        given(departmentService.getDepartmentById(any()))
                .willReturn(department);

        mockMvc.perform(get(PATH + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("departmentId").value(1))
                .andExpect(jsonPath("departmentName").value("some_dept_name"))
                .andExpect(jsonPath("departmentCode").value("some_dept_code"));

        verify(departmentService).getDepartmentById(any());
    }

    @Test
    void should_return404WhenGetDepartmentById() throws Exception {

        given(departmentService.getDepartmentById(any()))
                .willReturn(null);

        mockMvc.perform(get(PATH + "/123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(departmentService).getDepartmentById(any());
    }

    @Test
    void should_updateDepartment() throws Exception {
        DepartmentDto departmentDto = TestDepartmentData.departmentDto();
        DepartmentRequestDto departmentRequest = TestDepartmentData.departmentRequestDto();

        given(departmentService.updateDepartment(any(), any()))
                .willReturn(departmentDto);

        mockMvc.perform(patch(PATH + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(departmentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("departmentId").value(1))
                .andExpect(jsonPath("departmentName").value("some_dept_name"))
                .andExpect(jsonPath("departmentCode").value("some_dept_code"));

        verify(departmentService).updateDepartment(any(), any());
    }

    @Test
    void should_deleteDepartmentById() throws Exception {
        Long departmentId = 1L;
        String deletePath = PATH + "/1";
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(deletePath, departmentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(departmentService).deleteDepartment(departmentId);
    }


    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
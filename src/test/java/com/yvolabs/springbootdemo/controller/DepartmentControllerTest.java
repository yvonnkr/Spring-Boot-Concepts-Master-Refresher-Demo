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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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


    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
package com.yvolabs.springbootdemo.service;

import com.yvolabs.springbootdemo.dto.DepartmentDto;
import com.yvolabs.springbootdemo.dto.DepartmentRequestDto;
import com.yvolabs.springbootdemo.entity.Department;
import com.yvolabs.springbootdemo.repository.DepartmentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yvolabs.springbootdemo.mapper.DepartmentMapper.INSTANCE;

@Service
@Data
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    public final DepartmentRepository repository;

    @Override
    public DepartmentDto createDepartment(DepartmentRequestDto departmentRequest) {
        Department department = INSTANCE.createDepartmentFromDto(departmentRequest);
        repository.save(department);
        return INSTANCE.departmentDtoFromDepartment(department);

    }

    @Override
    public List<DepartmentDto> getDepartments() {
        List<Department> departments = repository.findAll();
        return departments.stream()
                .map(INSTANCE::departmentDtoFromDepartment)
                .toList();
    }
}

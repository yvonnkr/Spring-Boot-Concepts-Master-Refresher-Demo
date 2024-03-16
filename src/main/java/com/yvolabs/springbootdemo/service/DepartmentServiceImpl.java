package com.yvolabs.springbootdemo.service;

import com.yvolabs.springbootdemo.dto.DepartmentDto;
import com.yvolabs.springbootdemo.dto.DepartmentRequestDto;
import com.yvolabs.springbootdemo.entity.Department;
import com.yvolabs.springbootdemo.repository.DepartmentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return mapDepartmentsToDto(departments);
    }

    @Override
    public List<DepartmentDto> getDepartmentByNamePaginated(String departmentName, Pageable pageable) {
        Page<Department> allPaginated = repository.findAllByDepartmentName(
                departmentName,
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "departmentName"))
                )
        );

        List<Department> departments = allPaginated.getContent();
        return mapDepartmentsToDto(departments);


    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Optional<Department> departmentOpt = repository.findById(departmentId);
        return departmentOpt.map(INSTANCE::departmentDtoFromDepartment).orElse(null);

    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentRequestDto request) {
        Optional<Department> departmentOpt = repository.findById(departmentId);
        if (departmentOpt.isPresent()) {
            Department department = departmentOpt.get();
            INSTANCE.updateDepartmentFromDto(request, department);
            repository.save(department);
            return INSTANCE.departmentDtoFromDepartment(department);
        }

        return null;
    }

    private static List<DepartmentDto> mapDepartmentsToDto(List<Department> departments) {
        return departments.stream()
                .map(INSTANCE::departmentDtoFromDepartment)
                .toList();
    }


}

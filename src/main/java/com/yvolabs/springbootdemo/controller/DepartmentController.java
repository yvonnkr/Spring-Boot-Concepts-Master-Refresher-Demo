package com.yvolabs.springbootdemo.controller;

import com.yvolabs.springbootdemo.dto.DepartmentDto;
import com.yvolabs.springbootdemo.dto.DepartmentRequestDto;
import com.yvolabs.springbootdemo.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveDepartment(@RequestBody DepartmentRequestDto request) {
        DepartmentDto department = service.createDepartment(request);
        return entityWithLocation(department.getDepartmentId());

    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        return ResponseEntity.ok(service.getDepartments());

    }

    /**
     * Pagination - demo
     * search departments by name, pagination & sorting
     * /departments/search?departmentName=dev&page=0&size=3&sort=departmentId,desc
     */
    @GetMapping("/search")
    public ResponseEntity<List<DepartmentDto>> getDepartmentsPagination(@RequestParam(defaultValue = "") String departmentName, Pageable pageable) {
        List<DepartmentDto> departments = service.getDepartmentByNamePaginated(departmentName, pageable);
        return ResponseEntity.ok(departments);

    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<?> findDepartmentById(@PathVariable Long departmentId) {
        DepartmentDto department = service.getDepartmentById(departmentId);
        return department != null ? ResponseEntity.ok(department) :
                ResponseEntity.notFound().build();
    }

    @PatchMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long departmentId, @RequestBody DepartmentRequestDto request) {
        DepartmentDto department = service.updateDepartment(departmentId, request);
        return department != null ? ResponseEntity.ok(department) :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{departmentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDepartment(@PathVariable Long departmentId) {
        service.deleteDepartment(departmentId);
    }

    private ResponseEntity<Void> entityWithLocation(Object resourceId) {

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{resourceId}")
                .buildAndExpand(resourceId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

}

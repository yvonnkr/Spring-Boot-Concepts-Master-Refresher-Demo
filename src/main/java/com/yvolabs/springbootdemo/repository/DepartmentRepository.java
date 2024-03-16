package com.yvolabs.springbootdemo.repository;

import com.yvolabs.springbootdemo.entity.Department;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
   Page<Department> findAllByDepartmentName(@Nullable String departmentName , PageRequest pageRequest);
}

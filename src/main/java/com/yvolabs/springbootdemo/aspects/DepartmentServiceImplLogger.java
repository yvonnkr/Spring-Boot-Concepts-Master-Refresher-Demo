package com.yvolabs.springbootdemo.aspects;

import com.yvolabs.springbootdemo.dto.DepartmentDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j
public class DepartmentServiceImplLogger {
    @Pointcut("execution(* com.yvolabs.springbootdemo.service.DepartmentServiceImpl.createDepartment(..))")
    public void createDepartmentPointcut() {
    }

    @Pointcut("execution(* com.yvolabs.springbootdemo.service.DepartmentServiceImpl.getDepartments(..))")
    public void getDepartmentsPointcut() {
    }

    @Pointcut("execution(* com.yvolabs.springbootdemo.service.DepartmentServiceImpl.getDepartmentByNamePaginated(..))")
    public void getDepartmentsByNamePointcut() {
    }

    @AfterReturning(value = "createDepartmentPointcut()", returning = "department")
    public void createDepartmentLogger(JoinPoint jp, DepartmentDto department) {
        log.info("Department with id {} Saved", department.getDepartmentId());
    }

    @AfterReturning(value = "getDepartmentsPointcut()", returning = "departments")
    public void getDepartmentsLogger(JoinPoint jp, List<DepartmentDto> departments) {
        log.info("Get departments called: {} items returned", departments.size());
    }

    @AfterReturning(value = "getDepartmentsByNamePointcut()", returning = "departments")
    public void getDepartmentsByNameLogger(JoinPoint jp, List<DepartmentDto> departments) {
        log.info("Get departments by name paginated called: {} items returned", departments.size());
    }

    @AfterReturning(value = "execution(* com.yvolabs.springbootdemo.service.DepartmentServiceImpl.getDepartmentById(..))", returning = "response")
    public void getDepartmentByIdLogger(JoinPoint jp, DepartmentDto response) {
        String department = response != null ? response.toString() : "Department Not Found";
        log.info("{} Called: Response: {}", jp.getSignature().getName(), department);
    }

    @AfterReturning(value = "execution(* com.yvolabs.springbootdemo.service.DepartmentServiceImpl.updateDepartment(..))", returning = "response")
    public void updateDepartmentLogger(JoinPoint jp, DepartmentDto response) {
        String department = response != null ? response.toString() : "Department Not Found";
        log.info("{} Called: Response: {}", jp.getSignature().getName(), department);
    }
}
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

    @AfterReturning(value = "createDepartmentPointcut()", returning = "department")
    public void createDepartmentLogger(JoinPoint jp, DepartmentDto department) {
        log.info("Department with id {} Saved", department.getDepartmentId());
    }

    @AfterReturning(value = "getDepartmentsPointcut()", returning = "departments")
    public void getDepartmentsLogger(JoinPoint jp, List<DepartmentDto> departments) {
        log.info("Get departments called: {} items returned", departments.size());
    }
}
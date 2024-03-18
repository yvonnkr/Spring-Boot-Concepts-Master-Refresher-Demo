package com.yvolabs.springbootdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    @NotBlank(message = "departmentName is required")
    @Size(min = 3, message = "departmentName should have at least 3 characters")
    private String departmentName;

    private String departmentAddress;

    @NotBlank(message = "departmentCode is required")
    @Size(min = 3, message = "departmentCode should have at least 3 characters")
    private String departmentCode;


}

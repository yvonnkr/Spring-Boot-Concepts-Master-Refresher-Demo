package com.yvolabs.springbootdemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentRequestDto {
    @NotBlank(message = "departmentName is required")
    @Size(min = 3, message = "departmentName should have at least 3 characters")
    private String departmentName;
    private String departmentAddress;

    @NotBlank(message = "departmentCode is required")
    @Size(min = 3, message = "departmentCode should have at least 3 characters")
    private String departmentCode;
}

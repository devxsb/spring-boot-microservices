package com.safalifter.jobservice.request.job;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JobCreateRequest {
    @NotBlank(message = "Job name is required")
    private String name;
    private String description;
    @NotBlank(message = "Category id is required")
    private String categoryId;
    private String[] keys;
}

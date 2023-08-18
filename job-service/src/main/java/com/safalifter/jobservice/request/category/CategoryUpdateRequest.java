package com.safalifter.jobservice.request.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryUpdateRequest {
    @NotBlank(message = "Category id is required")
    private String id;
    private String name;
    private String description;
}

package com.safalifter.jobservice.request.category;

import lombok.Data;

@Data
public class CategoryCreateRequest {
    private String name;
    private String description;
}

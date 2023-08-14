package com.safalifter.jobservice.request.category;

import lombok.Data;

@Data
public class CategoryUpdateRequest {
    private String id;
    private String name;
    private String description;
}

package com.safalifter.jobservice.request.category;

import lombok.Getter;

@Getter
public class CategoryUpdateRequest {
    private String id;
    private String name;
    private String description;
}

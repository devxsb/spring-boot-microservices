package com.safalifter.jobservice.request.category;

import lombok.Getter;

@Getter
public class CategoryCreateRequest {
    private String name;
    private String description;
    private String[] imagesId;
}

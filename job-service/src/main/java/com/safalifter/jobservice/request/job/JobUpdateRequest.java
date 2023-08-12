package com.safalifter.jobservice.request.job;

import lombok.Getter;

@Getter
public class JobUpdateRequest {
    private String id;
    private String name;
    private String description;
    private String categoryId;
    private String[] keys;
    private String[] imagesId;
}

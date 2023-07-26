package com.safalifter.jobservice.request.job;

import lombok.Getter;

@Getter
public class JobCreateRequest {
    private String name;
    private String description;
    private String categoryId;
    private String[] keys;
}

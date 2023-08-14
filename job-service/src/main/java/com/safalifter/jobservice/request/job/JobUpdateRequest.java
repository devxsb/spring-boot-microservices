package com.safalifter.jobservice.request.job;

import lombok.Data;

@Data
public class JobUpdateRequest {
    private String id;
    private String name;
    private String description;
    private String categoryId;
    private String[] keys;
}

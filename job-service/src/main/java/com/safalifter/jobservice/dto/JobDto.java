package com.safalifter.jobservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobDto {
    private String id;
    private String name;
    private String description;
    private String categoryId;
    private List<AdvertDto> adverts;
    private List<String> keys;
    private List<String> imagesId;
}

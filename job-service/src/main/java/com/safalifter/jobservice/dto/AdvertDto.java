package com.safalifter.jobservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.safalifter.jobservice.enums.AdvertStatus;
import com.safalifter.jobservice.enums.Advertiser;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertDto {
    private String id;
    private String name;
    private String description;
    private int deliveryTime;
    private int price;
    private AdvertStatus status;
    private Advertiser advertiser;
    private String userId;
    private String jobId;
    private List<String> imagesId;
}

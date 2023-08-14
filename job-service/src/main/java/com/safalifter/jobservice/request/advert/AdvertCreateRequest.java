package com.safalifter.jobservice.request.advert;

import com.safalifter.jobservice.enums.Advertiser;
import lombok.Data;

@Data
public class AdvertCreateRequest {
    private String name;
    private String description;
    private int deliveryTime;
    private int price;
    private Advertiser advertiser;
    private String userId;
    private String jobId;
}

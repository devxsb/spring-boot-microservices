package com.safalifter.jobservice.request.offer;

import lombok.Data;

@Data
public class MakeAnOfferRequest {
    private String userId;
    private String advertId;
    private int offeredPrice;
}

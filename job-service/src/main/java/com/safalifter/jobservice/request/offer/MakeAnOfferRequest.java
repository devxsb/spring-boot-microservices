package com.safalifter.jobservice.request.offer;

import lombok.Getter;

@Getter
public class MakeAnOfferRequest {
    private String userId;
    private String advertId;
    private int offeredPrice;
}

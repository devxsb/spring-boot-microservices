package com.safalifter.jobservice.request.offer;

import com.safalifter.jobservice.enums.OfferStatus;
import lombok.Getter;

@Getter
public class OfferUpdateRequest {
    private String id;
    private int offeredPrice;
    private OfferStatus status;
}

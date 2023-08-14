package com.safalifter.jobservice.request.offer;

import com.safalifter.jobservice.enums.OfferStatus;
import lombok.Data;

@Data
public class OfferUpdateRequest {
    private String id;
    private int offeredPrice;
    private OfferStatus status;
}

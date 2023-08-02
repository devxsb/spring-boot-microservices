package com.safalifter.notificationservice.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendNotificationRequest {
    private String userId;
    private String offerId;
    private String message;
}

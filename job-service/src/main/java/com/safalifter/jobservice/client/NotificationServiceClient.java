package com.safalifter.jobservice.client;

import com.safalifter.jobservice.request.notification.SendNotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", path = "/v1/notification")
public interface NotificationServiceClient {
    @PostMapping("/send")
    void sendNotification(@RequestBody SendNotificationRequest request);
}

package com.safalifter.notificationservice.controller;

import com.safalifter.notificationservice.model.Notification;
import com.safalifter.notificationservice.request.SendNotificationRequest;
import com.safalifter.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationService notificationService;

    @PostMapping("/send")
    public void sendNotification(@RequestBody SendNotificationRequest request) {
        notificationService.save(request);
        simpMessagingTemplate.convertAndSendToUser(request.getUserId(), "/notification", request);
    }

    @GetMapping("/getAllNotificationsByUserId/{userId}")
    public ResponseEntity<List<Notification>> getAllNotificationsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.getAllNotificationByUserId(userId));
    }
}

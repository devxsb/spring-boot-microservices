package com.safalifter.notificationservice.service;

import com.safalifter.notificationservice.model.Notification;
import com.safalifter.notificationservice.repository.NotificationRepository;
import com.safalifter.notificationservice.request.SendNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void save(SendNotificationRequest request) {
        var notification = Notification.builder()
                .id(UUID.randomUUID().toString())
                .userId(request.getUserId())
                .offerId(request.getOfferId())
                .message(request.getMessage())
                .createdDate(new Date())
                .build();
        notificationRepository.save(notification);
    }

    public List<Notification> getAllNotificationByUserId(String id) {
        return notificationRepository.findAllByUserIdOrderByCreatedDateDesc(id);
    }
}

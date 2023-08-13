package com.safalifter.notificationservice.repository;

import com.safalifter.notificationservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, String> {
    List<Notification> findAllByUserIdOrderByCreationTimestampDesc(String id);
}

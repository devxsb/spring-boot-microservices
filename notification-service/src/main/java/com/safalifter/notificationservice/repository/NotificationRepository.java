package com.safalifter.notificationservice.repository;

import com.safalifter.notificationservice.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findAllByUserIdOrderByCreatedDateDesc(String id);
}

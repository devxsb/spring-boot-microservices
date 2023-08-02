package com.safalifter.notificationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Builder
@Getter
@Setter
public class Notification {
    @Id
    private String id;
    private String userId;
    private String offerId;
    private String message;

    @CreatedDate
    @JsonIgnore
    private Date createdDate;
}

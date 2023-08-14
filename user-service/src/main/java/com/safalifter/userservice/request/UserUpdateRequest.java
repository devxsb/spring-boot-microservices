package com.safalifter.userservice.request;

import com.safalifter.userservice.model.UserDetails;
import lombok.Data;

@Data
public class UserUpdateRequest {
    private String id;
    private String username;
    private String password;
    private UserDetails userDetails;
}

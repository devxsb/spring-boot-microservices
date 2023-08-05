package com.safalifter.authservice.dto;

import com.safalifter.authservice.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String username;
    private String email;
    private String password;
    private Role role;
}

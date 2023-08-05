package com.safalifter.userservice.controller;

import com.safalifter.userservice.dto.UserDto;
import com.safalifter.userservice.model.User;
import com.safalifter.userservice.request.RegisterRequest;
import com.safalifter.userservice.request.UserUpdateRequest;
import com.safalifter.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/save")
    public ResponseEntity<UserDto> save(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(modelMapper.map(userService.saveUser(request), UserDto.class));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class)).toList());
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(modelMapper.map(userService.getUserById(id), UserDto.class));
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(modelMapper.map(userService.getUserByEmail(email), UserDto.class));
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUserById(@RequestBody UserUpdateRequest request,
                                                  @RequestHeader(value = "loggedInUser") String username) {
        return ResponseEntity.ok(modelMapper.map(userService.updateUserById(request, username), UserDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id,
                                               @RequestHeader(value = "loggedInUser") String username) {
        userService.deleteUserById(id, username);
        return ResponseEntity.ok().build();
    }
}

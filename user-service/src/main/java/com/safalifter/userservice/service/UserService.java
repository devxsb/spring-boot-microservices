package com.safalifter.userservice.service;

import com.safalifter.userservice.enums.Active;
import com.safalifter.userservice.enums.Role;
import com.safalifter.userservice.exc.NotFoundException;
import com.safalifter.userservice.exc.UnauthorizedException;
import com.safalifter.userservice.model.User;
import com.safalifter.userservice.model.UserDetails;
import com.safalifter.userservice.repository.UserRepository;
import com.safalifter.userservice.request.RegisterRequest;
import com.safalifter.userservice.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(RegisterRequest request) {
        User toSave = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .active(Active.ACTIVE).build();
        return userRepository.save(toSave);
    }

    public List<User> getAll() {
        return userRepository.findAllByActive(Active.ACTIVE);
    }

    public User getUserById(String id) {
        return findUserById(id);
    }

    public User getUserByEmail(String email) {
        return findUserByEmail(email);
    }

    public User getUserByUsername(String username) {
        return findUserByUsername(username);
    }

    public User updateUserById(UserUpdateRequest request, String username) {
        User toUpdate = findUserById(request.getId());
        if (!toUpdate.getUsername().equals(username))
            throw new UnauthorizedException("You are not authorized to update this user");
        toUpdate.setUsername(Optional.ofNullable(request.getUsername()).orElse(toUpdate.getUsername()));
        toUpdate.setPassword(Optional.ofNullable(request.getPassword()).orElse(toUpdate.getPassword()));
        toUpdate.setUserDetails(updateUserDetails(toUpdate.getUserDetails(), request.getUserDetails()));
        return userRepository.save(toUpdate);
    }

    public void deleteUserById(String id, String username) {
        User toDelete = findUserById(id);
        if (!toDelete.getUsername().equals(username))
            throw new UnauthorizedException("You are not authorized to delete this user");
        toDelete.setActive(Active.INACTIVE);
        userRepository.save(toDelete);
    }

    protected User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    protected User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    protected User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    private UserDetails updateUserDetails(UserDetails toUpdate, UserDetails request) {
        if (request == null) return toUpdate;
        toUpdate = toUpdate == null ? new UserDetails() : toUpdate;
        toUpdate.setFirstName(Optional.ofNullable(request.getFirstName()).orElse(toUpdate.getFirstName()));
        toUpdate.setLastName(Optional.ofNullable(request.getLastName()).orElse(toUpdate.getLastName()));
        toUpdate.setPhoneNumber(Optional.ofNullable(request.getPhoneNumber()).orElse(toUpdate.getPhoneNumber()));
        toUpdate.setCountry(Optional.ofNullable(request.getCountry()).orElse(toUpdate.getCountry()));
        toUpdate.setCity(Optional.ofNullable(request.getCity()).orElse(toUpdate.getCity()));
        toUpdate.setAddress(Optional.ofNullable(request.getAddress()).orElse(toUpdate.getAddress()));
        toUpdate.setPostalCode(Optional.ofNullable(request.getPostalCode()).orElse(toUpdate.getPostalCode()));
        toUpdate.setAboutMe(Optional.ofNullable(request.getAboutMe()).orElse(toUpdate.getAboutMe()));
        toUpdate.setProfilePicture(Optional.ofNullable(request.getProfilePicture()).orElse(toUpdate.getProfilePicture()));
        return toUpdate;
    }
}

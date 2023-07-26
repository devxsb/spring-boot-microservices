package com.safalifter.userservice;

import com.safalifter.userservice.model.User;
import com.safalifter.userservice.repository.UserRepository;
import com.safalifter.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication implements CommandLineRunner {
    private final UserRepository userRepository;

    public UserServiceApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var admin = User.builder()
                .username("admin")
                .email("admin@gmail.com")
                .password("admin123").build();
        if (userRepository.findByUsername("admin").isEmpty()) userRepository.save(admin);
    }
}

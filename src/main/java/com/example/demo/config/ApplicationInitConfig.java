package com.example.demo.config;

import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.reponsitory.UserReponsitory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j

@Configuration
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;
    
    @Bean
    ApplicationRunner applicationRunner(UserReponsitory userReponsitory) {
        return args -> {
            if (userReponsitory.findByUserName("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());

                User user = User.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin123"))
                        // .roles(roles)
                        .build();
                userReponsitory.save(user);
                log.warn("admin user has been created with default password: admin123, please change it!");
            }
        };
    }
}

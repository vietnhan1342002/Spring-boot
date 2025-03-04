package com.example.demo.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.UserCreationRequestDTO;
import com.example.demo.dto.request.UserUpdateRequestDTO;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.UserMapper;
import com.example.demo.reponsitory.UserReponsitory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {
    UserReponsitory userReponsitory;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public List<UserResponse> getAllUser() {
        return userReponsitory.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public User createUser(UserCreationRequestDTO request) {

        if (userReponsitory.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userReponsitory.save(user);
    }

    public UserResponse getById(String userId) {
        return userMapper.toUserResponse(
            userReponsitory.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequestDTO request) {
        User user = userReponsitory.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        
        userMapper.updateUser(user, request);
        
        return userMapper.toUserResponse(userReponsitory.save(user));
    }

    public void deleteUser(String userId) {
        getById(userId);
        userReponsitory.deleteById(userId);
    }
}

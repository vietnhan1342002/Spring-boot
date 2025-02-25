package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.UserCreationRequestDTO;
import com.example.demo.dto.request.UserUpdateRequestDTO;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
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

    public List<User> getAllUser() {
        List<User> user = userReponsitory.findAll();
        if (user.size() == 0) {
            throw new RuntimeException("Don't have users");
        }
        return user;
    }

    public User createUser(UserCreationRequestDTO request) {

        if (userReponsitory.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

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

package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.demo.dto.request.UserCreationRequestDTO;
import com.example.demo.dto.request.UserUpdateRequestDTO;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequestDTO request);
    
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequestDTO request);
}

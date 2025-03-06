package com.example.demo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.UserCreationRequestDTO;
import com.example.demo.dto.request.ApiResponse;
import com.example.demo.dto.request.UserUpdateRequestDTO;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;
    
    @PostMapping()
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequestDTO request) {
        ApiResponse<User> userRespiResponseDTO = new ApiResponse<>();
        userRespiResponseDTO.setResult(userService.createUser(request));
        return userRespiResponseDTO;
    }
    
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    // @PreAuthorize("hasAuthority('READ_DATA')")
    public ApiResponse<List<UserResponse>> getAllUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return ApiResponse.<List<UserResponse>>builder().result(userService.getAllUser()).build();
    }


    @GetMapping({ "/info" })
    public ApiResponse<UserResponse> getInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getInfo())
                .build();
    }

    @GetMapping({ "/{userId}" })
    @PostAuthorize("returnObject.userName == authentication.name")
    public ApiResponse<UserResponse> getUserById(@PathVariable String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getById(userId))
                .build();
    }
    

    @PutMapping({ "/{userId}" })
    public UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequestDTO request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping({ "/{userId}" })
    public String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}

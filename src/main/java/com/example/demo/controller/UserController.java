package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.UserCreationRequestDTO;
import com.example.demo.dto.request.UserResponseDTO;
import com.example.demo.dto.request.UserUpdateRequestDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping()
    public UserResponseDTO<User> createUser(@RequestBody @Valid UserCreationRequestDTO request){
        UserResponseDTO<User> userRespiResponseDTO = new UserResponseDTO<>();
        userRespiResponseDTO.setResult(userService.createUser(request));
        return userRespiResponseDTO;
    }

    @GetMapping({"/{userId}"})
    public User getUserById(@PathVariable String userId){
        return userService.getById(userId);
    }

    @PutMapping({"/{userId}"})
    public User updateUser(@PathVariable String userId,@RequestBody UserUpdateRequestDTO request){
        return userService.updateUser(userId,request);
    }

    @DeleteMapping({"/{userId}"})
    public String deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}

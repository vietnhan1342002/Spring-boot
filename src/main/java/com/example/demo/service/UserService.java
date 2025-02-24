package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.UserCreationRequestDTO;
import com.example.demo.dto.request.UserUpdateRequestDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.reponsitory.UserReponsitory;

@Service
public class UserService {
    @Autowired
    private UserReponsitory userReponsitory;

    public List<User> getAllUser(){
        List<User> user = userReponsitory.findAll();
        if(user.size()==0){
            throw new RuntimeException("Don't have users");
        }
        return user;
    }

    public User createUser(UserCreationRequestDTO request){
        User user = new User();
        
        if(userReponsitory.existsByUserName(request.getUserName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userReponsitory.save(user);
    }

    public User getById(String userId){
        return userReponsitory.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
    }
    
    public User updateUser(String userId,UserUpdateRequestDTO request){
        User user = getById(userId);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userReponsitory.save(user);
    }

    public void deleteUser(String userId){
        getById(userId);
        userReponsitory.deleteById(userId);
    }
}

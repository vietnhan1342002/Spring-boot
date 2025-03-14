package com.example.demo.reponsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserReponsitory extends JpaRepository<User,String> {
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);
}
package com.example.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.InvalidatedJWT;

public interface InvalidatedRepository extends JpaRepository<InvalidatedJWT,String> {
    
}

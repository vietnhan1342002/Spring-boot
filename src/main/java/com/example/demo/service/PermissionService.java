package com.example.demo.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.PermissionCreationRequestDTO;
import com.example.demo.dto.response.PermissionResponse;
import com.example.demo.entity.Permission;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.reponsitory.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;
    
    public List<PermissionResponse> getAll(){
        return permissionRepository.findAll().stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public PermissionResponse create(PermissionCreationRequestDTO request){
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }

}

package com.example.demo.service;


import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.RoleCreationRequestDTO;
import com.example.demo.dto.response.RoleResponse;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.reponsitory.PermissionRepository;
import com.example.demo.reponsitory.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;
    
    public List<RoleResponse> getAll(){
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    public RoleResponse create(RoleCreationRequestDTO request){
        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public void delete(String role){
        roleRepository.deleteById(role);
    }

}

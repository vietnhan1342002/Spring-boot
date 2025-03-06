package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.dto.request.RoleCreationRequestDTO;
import com.example.demo.dto.response.RoleResponse;
import com.example.demo.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleCreationRequestDTO requestDTO);
    RoleResponse toRoleResponse(Role role);
}

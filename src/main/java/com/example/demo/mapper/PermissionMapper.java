package com.example.demo.mapper;


import org.mapstruct.Mapper;

import com.example.demo.dto.request.PermissionCreationRequestDTO;
import com.example.demo.dto.response.PermissionResponse;
import com.example.demo.entity.Permission;


@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionCreationRequestDTO request);
    PermissionResponse toPermissionResponse(Permission permission);
}

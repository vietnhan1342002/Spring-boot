package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.ApiResponse;
import com.example.demo.dto.request.RoleCreationRequestDTO;
import com.example.demo.dto.response.RoleResponse;
import com.example.demo.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @GetMapping()
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @PostMapping()
    ApiResponse<RoleResponse> create(@RequestBody RoleCreationRequestDTO requestDTO) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(requestDTO))
                .build();
    }

    @DeleteMapping({ "/{roleId}" })
    ApiResponse<Void> delete(@PathVariable String roleId) {
        roleService.delete(roleId);
        return ApiResponse.<Void>builder().build();
    }

}

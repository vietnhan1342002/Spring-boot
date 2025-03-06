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
import com.example.demo.dto.request.PermissionCreationRequestDTO;
import com.example.demo.dto.response.PermissionResponse;
import com.example.demo.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;

    @GetMapping()
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @PostMapping()
    ApiResponse<PermissionResponse> create(@RequestBody PermissionCreationRequestDTO requestDTO) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(requestDTO))
                .build();
    }

    @DeleteMapping({ "/{permissionId}" })
    ApiResponse<Void> delete(@PathVariable String permissionId) {
        permissionService.delete(permissionId);
        return ApiResponse.<Void>builder().build();
    }

}

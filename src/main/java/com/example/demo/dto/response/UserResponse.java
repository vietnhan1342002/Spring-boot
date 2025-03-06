package com.example.demo.dto.response;

import java.time.LocalDate;
import java.util.Set;

import com.example.demo.entity.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    String id;
    String userName;
    String firstName;
    String lastName;
    LocalDate dob;
    Set<RoleResponse> roles;
}

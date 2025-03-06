package com.example.demo.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.validator.DoBContraint;

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
public class UserUpdateRequestDTO {
    String firstName;
    String lastName;
    @DoBContraint(min=18,message = "INVALID_DOB")
    LocalDate dob;
    List<String> roles;
}

package com.example.demo.dto.request;

import java.time.LocalDate;


import jakarta.validation.constraints.Size;
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
public class UserCreationRequestDTO {
    @Size(min = 3,message = "USERNAME_INVALID")
    String userName;
        
    @Size(min = 8,message = "PASSWORD_INVALID")
    String password;

    String firstName;
    String lastName;
    LocalDate dob;
}

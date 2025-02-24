package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.dto.request.UserResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<UserResponseDTO<Void>> handlingRuntimeException(Exception exception) {

        UserResponseDTO<Void> userResponseDTO = new UserResponseDTO<Void>();

        userResponseDTO.setMessage(ErrorCode.UNCATEGORIZED.getMessage());
        userResponseDTO.setCode(ErrorCode.UNCATEGORIZED.getCode());

        return ResponseEntity.badRequest().body(userResponseDTO);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<UserResponseDTO<Void>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        UserResponseDTO<Void> userResponseDTO = new UserResponseDTO<Void>();

        userResponseDTO.setMessage(errorCode.getMessage());
        userResponseDTO.setCode(errorCode.getCode());

        return ResponseEntity.badRequest().body(userResponseDTO);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<UserResponseDTO<Void>> handlingMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        UserResponseDTO<Void> userResponseDTO = new UserResponseDTO<Void>();
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.KEY_INVALID;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {
            // TODO: handle exception
        }
        userResponseDTO.setMessage(errorCode.getMessage());
        userResponseDTO.setCode(errorCode.getCode());
        return ResponseEntity.badRequest().body(userResponseDTO);
    }


}

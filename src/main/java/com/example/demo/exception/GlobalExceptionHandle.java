package com.example.demo.exception;

import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.dto.request.ApiResponse;

import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<Void>> handlingRuntimeException(Exception exception) {

        ApiResponse<Void> userResponseDTO = new ApiResponse<Void>();

        userResponseDTO.setMessage(ErrorCode.UNCATEGORIZED.getMessage());
        userResponseDTO.setCode(ErrorCode.UNCATEGORIZED.getCode());

        return ResponseEntity.badRequest().body(userResponseDTO);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<Void>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse<Void> userResponseDTO = new ApiResponse<Void>();

        userResponseDTO.setMessage(errorCode.getMessage());
        userResponseDTO.setCode(errorCode.getCode());

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(userResponseDTO);
    }

    @SuppressWarnings({ "null", "unchecked" })
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Void>> handlingMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        ApiResponse<Void> userResponseDTO = new ApiResponse<Void>();
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.KEY_INVALID;
        Map<String, Object>attributes = null;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
            var constraintViolation = exception.getBindingResult()
            .getAllErrors().getFirst().unwrap(ConstraintViolation.class);
            attributes = constraintViolation.getConstraintDescriptor().getAttributes();
        } catch (IllegalArgumentException e) {
        }
        userResponseDTO.setMessage(Objects.nonNull(attributes)
        ?mapAttribute(errorCode.getMessage(), attributes)
        :errorCode.getMessage());
        userResponseDTO.setCode(errorCode.getCode());
        return ResponseEntity.badRequest().body(userResponseDTO);
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = AuthorizationDeniedException.class)
    ResponseEntity<ApiResponse> handlingAuthorizationDeniedException(AuthorizationDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
            ApiResponse.builder()
            .code(errorCode.getCode())
            .message(errorCode.getMessage())
            .build()
        );
    }

    private String mapAttribute(String message, Map<String, Object>attributes){
        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));
        return message.replace("{"+MIN_ATTRIBUTE+"}", minValue);
    }

}

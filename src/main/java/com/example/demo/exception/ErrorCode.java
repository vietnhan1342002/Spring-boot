package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_EXISTED(409,"User existed.",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(404,"User not exist.",HttpStatus.NOT_FOUND),
    UNCATEGORIZED(500,"Uncategorized exception",HttpStatus.INTERNAL_SERVER_ERROR),
    KEY_INVALID(400,"Invalid message key",HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(400,"UserName must be at least 3 characters",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(400,"Password must be at least 8 characters",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(401,"Unauthenticated",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(403,"You do not have permission",HttpStatus.FORBIDDEN),
    INVALID_DOB(400,"Invalid date of birth",HttpStatus.BAD_REQUEST)
    ;

    ErrorCode(int code, String message,HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode=httpStatusCode;
    }
    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;     
}

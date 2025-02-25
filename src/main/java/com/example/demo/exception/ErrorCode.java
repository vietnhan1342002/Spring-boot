package com.example.demo.exception;

public enum ErrorCode {
    USER_EXISTED(409,"User existed."),
    USER_NOT_EXISTED(400,"User not exist."),
    UNCATEGORIZED(500,"Uncategorized exception"),
    KEY_INVALID(400,"Invalid message key"),
    USERNAME_INVALID(400,"UserName must be at least 3 characters"),
    PASSWORD_INVALID(400,"Password must be at least 8 characters"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    private int code;
    private String message;
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    
}

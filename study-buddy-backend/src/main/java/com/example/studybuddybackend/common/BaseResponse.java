package com.example.studybuddybackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 * @author Willow
 **/
@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1825407555701518649L;

    private int code;

    private T data;

    private String message;

    private String description;

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }

}

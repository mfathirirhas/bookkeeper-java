package com.ledger.controller;

public class BaseResponse<T> {
    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private T data;

    private String error;

    public BaseResponse(int code, T data, String message, String error) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.error = error;
    }

    public static <T> BaseResponse<T> Ok(T data, int... code) {
        var httpCode = 200;
        if (code.length > 0) {
            httpCode = code[0];
        }
        return new BaseResponse<>(httpCode, data, "SUCCESS", null);
    }

    public static <T> BaseResponse<T> InternalError(String message, String error, int... code) {
        var httpCode = 500;
        if (code.length > 0) {
            httpCode = code[0];
        }
        return new BaseResponse<>(httpCode, null, message, error);
    }

    public static <T> BaseResponse<T> ClientError(String message, String error, int... code) {
        var httpCode = 400;
        if (code.length > 0) {
            httpCode = code[0];
        }
        return new BaseResponse<>(httpCode, null, message, error);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }
}


package com.journal.controller;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private T data;

    private String error;
}

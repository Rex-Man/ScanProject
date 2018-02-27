package com.oocl.manre.fragmentstudy.entity;

/**
 * Created by manre on 21/02/2018.
 */

public class ResponseEntity<T> {
    private String errorCode;
    private T data;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

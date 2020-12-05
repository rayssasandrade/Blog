package com.spring.codeblog.utils;

public class Response<T>  {

    private Boolean r;
    private T data;

    public Response(Boolean r) {
        this.r = r;
    }

    public Boolean getR() {
        return r;
    }

    public void setR(Boolean r) {
        this.r = r;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
package com.xuxin.model;

import java.io.Serializable;

public class Result implements Serializable {

    private static final String SUCCESS = "success";

    private static final String FAILED = "failed";

    private String status;

    private Object data;

    private Result(){}

    private Result(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success(Object object){
        return new Result(SUCCESS,object);
    }

    public static Result failed(Object object) {
        return new Result(FAILED,object);
    }
}

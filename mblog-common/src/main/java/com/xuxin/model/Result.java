package com.xuxin.model;

import com.xuxin.constants.ResultCodeConstants;

import java.io.ObjectOutput;
import java.io.Serializable;

public class Result implements Serializable {

    private static final String SUCCESS = "success";

    private static final String FAILED = "failed";

    private int code;

    private String status;

    private Object data;

    private Result(){}

    private Result(int code, String status, Object data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
        return new Result(ResultCodeConstants.SUCCESS, SUCCESS,object);
    }

    public static Result success(int code, Object object){
        return new Result(ResultCodeConstants.SUCCESS,SUCCESS,object);
    }

    public static Result failed(int code, Object object) {
        return new Result(code, FAILED, object);
    }

    public static Result failed(Object object) {
        return new Result(ResultCodeConstants.FAILED, FAILED,object);
    }
}

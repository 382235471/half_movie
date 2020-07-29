package com.half.movie.common.utils;

public class ResponseData {

    private String status;
    private String msg;
    private Object data;

    public ResponseData(String status,Object data) {
        this.data = data;
        this.status = status;
    }

    public ResponseData(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseData(String status, Object data, String msg) {
        this.data = data;
        this.status = status;
        this.msg = msg;
    }

    public static ResponseData fail(String status,String msg) {
        return new ResponseData(status, msg);
    }

    public static ResponseData success(String status,Object data) {
        return new ResponseData(status,data);
    }
    public static ResponseData success(String status,String msg) {
        return new ResponseData(status,msg);
    }

    public static ResponseData success(String status, Object data, String msg) {
        return new ResponseData(status,data, msg);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
